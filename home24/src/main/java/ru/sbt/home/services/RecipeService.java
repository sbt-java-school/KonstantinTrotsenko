package ru.sbt.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbt.home.dao.recipedao.RecipeDao;
import ru.sbt.home.dto.Recipe;

import java.util.List;
import java.util.Objects;

/**
 * Сервис для Recipe
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see RecipeDao
 */
@Service
public class RecipeService {
    private List<Recipe> recipes;
    private RecipeDao recipeDao;

    @Autowired
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Transactional(readOnly = true)
    public List<Recipe> findRecipesByName(String recipeName) {
        recipes = recipeDao.findRecipesByName(recipeName);
        return recipes;
    }

    @Transactional
    public void deleteRecipe(Integer recipeId) {
        recipeDao.deleteRecipeFromCookbook(recipeId);
        recipeDao.deleteRecipe(recipeId);
    }

    @Transactional
    public void updateRecipeName(Integer id, String newName) {
        recipeDao.updateRecipeName(id, newName);
    }

    @Transactional
    public void updateRecipeDescription(Integer id, String newDescription) {
        recipeDao.updateRecipeDescription(id, newDescription);
    }

    /**
     * Метод проверяет налицие рецепта в выборке
     *
     * @param recipeId recipe id
     * @return true если содержиться в выборке
     */
    public boolean containsRecipe(Integer recipeId) {
        if (recipes != null) {
            for (Recipe recipe : recipes) {
                if (Objects.equals(recipe.getId(), recipeId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
