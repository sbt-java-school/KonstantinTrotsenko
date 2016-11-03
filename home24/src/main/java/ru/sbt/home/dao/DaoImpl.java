package ru.sbt.home.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sbt.home.core.CookbookSheet;
import ru.sbt.home.core.CookbookSheetSimple;
import ru.sbt.home.core.Ingredient;
import ru.sbt.home.core.Recipe;
import ru.sbt.home.view.searchbranch.SearchControllerRedactor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Repository
public class DaoImpl implements Dao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoImpl.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        // namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Recipe> getRecipesWithParam(String param) {
        List<Recipe> recipes = new ArrayList<>();
        List<Map<String, Object>> recipesFromDb = jdbcTemplate.queryForList("SELECT * FROM RECIPES WHERE RECIPE LIKE ? " +
                        "ORDER BY ID",
                "%" + param + "%");
        for (Map<String, Object> stringObjectMap : recipesFromDb) {
            recipes.add(new Recipe((Integer) stringObjectMap.get("ID"),
                    (String) stringObjectMap.get("RECIPE"),
                    (String) stringObjectMap.get("DESCRIPTION")));
        }
        return recipes;
    }

    @Override
    public List<CookbookSheetSimple> getCookbookSheetWithRecipeId(Integer recipeId) {
        List<CookbookSheetSimple> ingredients = new ArrayList<>();
        List<Map<String, Object>> dataFromDb = jdbcTemplate.queryForList(
                "SELECT C.IDRECIPE, R.RECIPE, R.DESCRIPTION, C.IDINGREDIENT, I.INGREDIENT, C.COUNT, C.UNIT " +
                        "FROM COOKBOOK C " +
                        "INNER JOIN INGREDIENTS I ON C.IDINGREDIENT = I.ID " +
                        "INNER JOIN RECIPES R ON C.IDRECIPE = R.ID " +
                        "WHERE C.IDRECIPE = ? ORDER BY IDINGREDIENT", recipeId);
        if (dataFromDb.isEmpty()) {
            return null;
        }
        for (Map<String, Object> stringObjectMap : dataFromDb) {
            LOGGER.info(stringObjectMap.toString());
            ingredients.add(new CookbookSheetSimple((Integer) stringObjectMap.get("IDRECIPE"), (String) stringObjectMap.get("RECIPE"),
                    (String) stringObjectMap.get("DESCRIPTION"), (Integer) stringObjectMap.get("IDINGREDIENT"), (
                    String) stringObjectMap.get("INGREDIENT"), (String) stringObjectMap.get("COUNT"), (
                    String) stringObjectMap.get("UNIT")));
        }
        return ingredients;
    }

    @Override
    public void updateRecipeName(Integer recipeId, String newName) {
        jdbcTemplate.update("UPDATE RECIPES SET RECIPE = ? WHERE ID = ?", newName, recipeId);
    }

    @Override
    public void updateRecipeDescription(Integer recipeId, String newDescription) {
        jdbcTemplate.update("UPDATE RECIPES SET DESCRIPTION = ? WHERE ID = ?", newDescription, recipeId);
    }

    @Override
    public void deleteIngredientFromCookbook(Integer recipeId, String ingredientId) {
        jdbcTemplate.update("DELETE FROM COOKBOOK WHERE IDRECIPE = ? AND IDINGREDIENT = ?", recipeId, ingredientId);
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
    public void createNewIngredientToCookbook(Integer recipeId, Integer ingredientId, String ingredientCount, String ingredientUnit) {
        jdbcTemplate.update("INSERT INTO COOKBOOK (IDRECIPE, IDINGREDIENT, COUNT, UNIT) VALUES(?,?,?,?)",
                recipeId, ingredientId, ingredientCount, ingredientUnit);
    }

    @Override
    public void updateIngredientInCookbook(String ingredientId, String ingredientCount, String ingredientUnit) {
        jdbcTemplate.update("UPDATE COOKBOOK SET COUNT = ?, UNIT = ? WHERE IDINGREDIENT = ?", ingredientCount, ingredientUnit,
                ingredientId);
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
    public Integer createNewRecipe(String recipeName, String recipeDescription) {
        jdbcTemplate.update("INSERT INTO RECIPES (RECIPE, DESCRIPTION) VALUES (?,?)", recipeName, recipeDescription);
        List<Map<String, Object>> dataFromDb = jdbcTemplate.queryForList("SELECT ID FROM RECIPES WHERE RECIPE = ? " +
                "AND DESCRIPTION = ?", recipeName, recipeDescription);
        Integer ingredientId = (Integer) dataFromDb.get(dataFromDb.size() - 1).get("ID");
        return ingredientId;
    }

    @Override
    public void deleteCookbookSheet(Integer recipeId) {
        jdbcTemplate.update("DELETE FROM COOKBOOK WHERE IDRECIPE = ?", recipeId);
    }

    @Override
    public void deleteRecipe(Integer recipeId) {
        jdbcTemplate.update("DELETE FROM RECIPES WHERE ID = ?", recipeId);
    }
}
