package ru.sbt.home.dao.recipedao;

import org.springframework.stereotype.Component;
import ru.sbt.home.dto.Recipe;

import java.util.List;

/**
 * Recipe Dao
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public interface RecipeDao {
    /**
     * Поиск рецепта по наличию слова или его части
     * @param recipeName искомое слово
     * @return все найденные рецепты
     */
    List<Recipe> findRecipesByName(String recipeName);
    /**
     * Удаление записи в книге рецептов
     * @param recipeId
     */
    void deleteRecipeFromCookbook(Integer recipeId);

    /**
     * Удаление рецепта
     * @param recipeId
     */
    void deleteRecipe(Integer recipeId);

    /**
     * Создание нового рецепта
     * @param recipeName
     * @param recipeDescription
     * @return id рецепта
     */
    Integer createNewRecipe(String recipeName, String recipeDescription);

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
}
