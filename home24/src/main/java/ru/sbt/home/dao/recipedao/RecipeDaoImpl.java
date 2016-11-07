package ru.sbt.home.dao.recipedao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sbt.home.dto.Recipe;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Реализация RecipeDao
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see RecipeDao
 */
@Repository
public class RecipeDaoImpl implements RecipeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeDaoImpl.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        // namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Recipe> findRecipesByName(String recipeName) {
        List<Recipe> recipes = new ArrayList<>();
        List<Map<String, Object>> dataFromDb = jdbcTemplate.queryForList("SELECT * FROM RECIPES WHERE RECIPE LIKE ? " +
                        "ORDER BY ID",
                "%" + recipeName + "%");
        for (Map<String, Object> stringObjectMap : dataFromDb) {
            recipes.add(new Recipe((Integer) stringObjectMap.get("ID"),
                    (String) stringObjectMap.get("RECIPE"),
                    (String) stringObjectMap.get("DESCRIPTION")));
        }
        return recipes;
    }

    @Override
    public void deleteRecipeFromCookbook(Integer recipeId) {
        jdbcTemplate.update("DELETE FROM COOKBOOK WHERE IDRECIPE = ?", recipeId);
    }

    @Override
    public void deleteRecipe(Integer recipeId) {
        jdbcTemplate.update("DELETE FROM RECIPES WHERE ID = ?", recipeId);
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
    public void updateRecipeName(Integer recipeId, String newName) {
        jdbcTemplate.update("UPDATE RECIPES SET RECIPE = ? WHERE ID = ?", newName, recipeId);
    }

    @Override
    public void updateRecipeDescription(Integer recipeId, String newDescription) {
        jdbcTemplate.update("UPDATE RECIPES SET DESCRIPTION = ? WHERE ID = ?", newDescription, recipeId);
    }
}
