package ru.sbt.home.dao.ingredientdao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sbt.home.dao.recipedao.RecipeDaoImpl;
import ru.sbt.home.dto.Ingredient;
import ru.sbt.home.dto.Recipe;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация IngredientDao
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see IngredientDao
 */
@Repository
public class IngredientDaoImpl implements IngredientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeDaoImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer findIngredientByName(String ingredientName) {
        List<Map<String, Object>> dataFromDb = jdbcTemplate.queryForList("SELECT ID FROM INGREDIENTS WHERE INGREDIENT = ?",
                ingredientName);
        if (dataFromDb.isEmpty()) {
            return null;
        }
        Integer ingredientId = (Integer) dataFromDb.get(0).get("ID");
        return ingredientId;
    }

    @Override
    public Integer createNewIngredient(String ingredientName) {
        jdbcTemplate.update("INSERT INTO INGREDIENTS (INGREDIENT) VALUES (?)", ingredientName);
        List<Map<String, Object>> dataFromDb = jdbcTemplate.queryForList("SELECT ID FROM INGREDIENTS WHERE INGREDIENT = ?",
                ingredientName);
        Integer ingredientId = (Integer) dataFromDb.get(dataFromDb.size() - 1).get("ID");
        return ingredientId;
    }

    @Override
    public boolean createNewIngredientToCookbook(Integer recipeId, Integer ingredientId, String ingredientCount, String ingredientUnit) {
        int result = jdbcTemplate.update("INSERT INTO COOKBOOK (IDRECIPE, IDINGREDIENT, COUNT, UNIT) VALUES(?,?,?,?)",
                recipeId, ingredientId, ingredientCount, ingredientUnit);
        return result == 1;
    }

    @Override
    public Map<Recipe, List<Ingredient>> getRecipeWithIngredientsByRecipeId(Integer recipeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        Map<Recipe, List<Ingredient>> recipeWithIngredients = new HashMap<>();
        List<Map<String, Object>> dataFromDb = jdbcTemplate.queryForList(
                "SELECT C.IDRECIPE, R.RECIPE, R.DESCRIPTION, C.IDINGREDIENT, I.INGREDIENT, C.COUNT, C.UNIT " +
                        "FROM COOKBOOK C " +
                        "INNER JOIN INGREDIENTS I ON C.IDINGREDIENT = I.ID " +
                        "INNER JOIN RECIPES R ON C.IDRECIPE = R.ID " +
                        "WHERE C.IDRECIPE = ? ORDER BY IDINGREDIENT", recipeId);
        if (dataFromDb.isEmpty()) {
            return null;
        }
        Recipe recipe = new Recipe((Integer) dataFromDb.get(0).get("IDRECIPE"), (String) dataFromDb.get(0).get("RECIPE"),
                (String) dataFromDb.get(0).get("DESCRIPTION"));
        for (Map<String, Object> stringObjectMap : dataFromDb) {
            LOGGER.info(stringObjectMap.toString());
            ingredients.add(new Ingredient((Integer) stringObjectMap.get("IDINGREDIENT"),
                    (String) stringObjectMap.get("INGREDIENT"), (String) stringObjectMap.get("COUNT"),
                    (String) stringObjectMap.get("UNIT")));

        }
        recipeWithIngredients.put(recipe, ingredients);
        return recipeWithIngredients;
    }

    @Override
    public void deleteIngredientFromCookbook(Integer recipeId, String ingredientId) {
        jdbcTemplate.update("DELETE FROM COOKBOOK WHERE IDRECIPE = ? AND IDINGREDIENT = ?", recipeId, ingredientId);
    }

    @Override
    public void updateIngredientInCookbook(String ingredientId, String ingredientCount, String ingredientUnit) {
        jdbcTemplate.update("UPDATE COOKBOOK SET COUNT = ?, UNIT = ? WHERE IDINGREDIENT = ?", ingredientCount, ingredientUnit,
                ingredientId);
    }
}
