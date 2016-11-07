package ru.sbt.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.home.dao.ingredientdao.IngredientDao;
import ru.sbt.home.dao.recipedao.RecipeDao;
import ru.sbt.home.dto.Ingredient;
import ru.sbt.home.dto.Recipe;

import java.util.List;
import java.util.Map;

/**
 * Сервис для Ingredient
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see RecipeDao
 * @see IngredientDao
 */
@Service
public class IngredientService {
    private IngredientDao ingredientDao;
    private RecipeDao recipeDao;

    @Autowired
    public IngredientService(IngredientDao ingredientDao, RecipeDao recipeDao) {
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
    }

    @Transactional
    public boolean saveReсipeWithIngredients(Recipe recipe, List<Ingredient> ingredients) {
        List<Ingredient> ingredientsWithRealId = checkRealIngredientId(ingredients);
        Integer recipeId = recipeDao.createNewRecipe(recipe.getRecipe(), recipe.getDescription());
        for (Ingredient ingredient : ingredientsWithRealId) {
            boolean result = ingredientDao.createNewIngredientToCookbook(recipeId, ingredient.getId(),
                    ingredient.getCountIngredient(), ingredient.getUnitIngredient());
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @Transactional(readOnly = true)
    public Map<Recipe, List<Ingredient>> getRecipeWithIngredientsByRecipeId(Integer id) {
        return ingredientDao.getRecipeWithIngredientsByRecipeId(id);
    }

    @Transactional
    public void deleteIngredientFromCookbook(Integer id, String ingredientId) {
        ingredientDao.deleteIngredientFromCookbook(id, ingredientId);
    }

    @Transactional
    public void updateIngredientInCookbook(String ingredientId, String ingredientCount, String ingredientUnit) {
        ingredientDao.updateIngredientInCookbook(ingredientId, ingredientCount, ingredientUnit);
    }

    public Integer findIngredientByName(String ingredientName) {
        return ingredientDao.findIngredientByName(ingredientName);
    }

    @Transactional
    public void createNewIngredientToCookbookNewId(Integer id, String ingredientName, String ingredientCount, String ingredientUnit) {
        Integer newIngredientId = ingredientDao.createNewIngredient(ingredientName);
        ingredientDao.createNewIngredientToCookbook(id, newIngredientId, ingredientCount, ingredientUnit);

    }

    @Transactional
    public void createNewIngredientToCookbook(Integer id, Integer sameIngredientInDbId, String ingredientCount, String ingredientUnit) {
        ingredientDao.createNewIngredientToCookbook(id, sameIngredientInDbId, ingredientCount, ingredientUnit);
    }

    /**
     * Метод ищет введенные пользователем ингредиенты в базе данных, если такие уже есть, он присваивает их id - вновь
     * соданным, если нет, добавляет их и присваивает новые id
     *
     * @param ingredients лист с ингредиентами, id которых установленн условно
     * @return лист с ингредиентами, id которых установленн исходя из наличия в DB
     */
    private List<Ingredient> checkRealIngredientId(List<Ingredient> ingredients) {
        List<Ingredient> ingredientsWithRealId;
        ingredientsWithRealId = ingredients;
        for (Ingredient ingredient : ingredientsWithRealId) {
            Integer realId = ingredientDao.findIngredientByName(ingredient.getName());
            if (realId != null) {
                ingredient.setId(realId);
            } else {
                Integer newRealId = ingredientDao.createNewIngredient(ingredient.getName());
                ingredient.setId(newRealId);
            }
        }
        return ingredientsWithRealId;
    }


}
