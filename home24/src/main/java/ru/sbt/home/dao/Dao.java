package ru.sbt.home.dao;

import org.springframework.stereotype.Component;
import ru.sbt.home.core.CookbookSheetSimple;
import ru.sbt.home.core.Recipe;

import java.util.List;

/**
 * Dao
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public interface Dao {
    /**
     * Поиск рецепта по наличию слова или его части
     * @param param искомое слово
     * @return все найденные рецепты
     */
    List<Recipe> getRecipesWithParam(String param);

    /**
     * Поиск записи в книге рецептов по recipeId
     * @param recipeId
     * @return все записи с даннм id
     */
    List<CookbookSheetSimple> getCookbookSheetWithRecipeId(Integer recipeId);

    /**
     * Обновление названия рецепта
     * @param recipeId
     * @param newName
     */
    void updateRecipeName(Integer recipeId, String newName);

    /**
     * Обновление описнания рецепта
     * @param recipeId
     * @param newDescription
     */
    void updateRecipeDescription(Integer recipeId, String newDescription);

    /**
     * Удаление ингредиента из книги рецептов
     * @param recipeId
     * @param ingredientId
     */
    void deleteIngredientFromCookbook(Integer recipeId, String ingredientId);

    /**
     * Создание нового ингредиента
     * @param ingredientName
     * @return id нового ингредиента
     */
    Integer createNewIngredient(String ingredientName);

    /**
     * Добавление нового ингредиента в книгу рецептов
     * @param recipeId
     * @param ingredientId
     * @param ingredientCount
     * @param ingredientUnit
     */
    void createNewIngredientToCookbook(Integer recipeId, Integer ingredientId, String ingredientCount, String ingredientUnit);

    /**
     * Обновление ингредиента в книге рцептов
     * @param ingredientId
     * @param ingredientCount
     * @param ingredientUnit
     */
    void updateIngredientInCookbook(String ingredientId, String ingredientCount, String ingredientUnit);

    /**
     * Поиск ингредиента по имени
     * @param ingredientName
     * @return id искомого инредиента
     */
    Integer findIngredientByName(String ingredientName);

    /**
     * Создание нового рецепта
     * @param recipeName
     * @param recipeDescription
     * @return id рецепта
     */
    Integer createNewRecipe(String recipeName, String recipeDescription);

    /**
     * Удаление записи в книге рецептов
     * @param recipeId
     */
    void deleteCookbookSheet(Integer recipeId);

    /**
     * Удаление рецепта
     * @param recipeId
     */
    void deleteRecipe(Integer recipeId);
}
