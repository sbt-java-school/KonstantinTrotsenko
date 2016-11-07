package ru.sbt.home.dao.ingredientdao;

import org.springframework.stereotype.Component;
import ru.sbt.home.dto.Ingredient;
import ru.sbt.home.dto.Recipe;

import java.util.List;
import java.util.Map;

/**
 * Ingredient Dao
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public interface IngredientDao {

    /**
     * Поиск ингредиента по имени
     *
     * @param ingredientName
     * @return id искомого инредиента
     */
    Integer findIngredientByName(String ingredientName);

    /**
     * Создание нового ингредиента
     *
     * @param ingredientName
     * @return id нового ингредиента
     */
    Integer createNewIngredient(String ingredientName);

    /**
     * Добавление нового ингредиента в книгу рецептов
     *
     * @param recipeId
     * @param ingredientId
     * @param ingredientCount
     * @param ingredientUnit
     */
    boolean createNewIngredientToCookbook(Integer recipeId, Integer ingredientId, String ingredientCount, String ingredientUnit);

    /**
     * Поиск записи в книге рецептов по recipeId
     *
     * @param recipeId
     * @return все записи с даннм id
     */
    Map<Recipe, List<Ingredient>> getRecipeWithIngredientsByRecipeId(Integer recipeId);

    /**
     * Удаление ингредиента из книги рецептов
     * @param recipeId
     * @param ingredientId
     */
    void deleteIngredientFromCookbook(Integer recipeId, String ingredientId);

    /**
     * Обновление ингредиента в книге рцептов
     * @param ingredientId
     * @param ingredientCount
     * @param ingredientUnit
     */
    void updateIngredientInCookbook(String ingredientId, String ingredientCount, String ingredientUnit);
}
