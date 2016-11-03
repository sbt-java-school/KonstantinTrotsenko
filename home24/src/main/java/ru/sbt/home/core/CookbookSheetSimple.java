package ru.sbt.home.core;

/**
 * Класс для создания упрощенных объектов для книги рецептов (таблица Cookbook)
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class CookbookSheetSimple {
    private Integer recipeId;
    private String recipeName;
    private String recipeDescription;
    private Integer ingredientId;
    private String ingredientName;
    private String countIngredient;
    private String unitIngredient;

    public CookbookSheetSimple(Integer recipeId, String recipeName, String recipeDescription, Integer ingredientId,
                               String ingredientName, String countIngredient, String unitIngredient) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.countIngredient = countIngredient;
        this.unitIngredient = unitIngredient;
    }

    public CookbookSheetSimple(Integer ingredientId, String ingredientName, String countIngredient, String unitIngredient) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.countIngredient = countIngredient;
        this.unitIngredient = unitIngredient;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getCountIngredient() {
        return countIngredient;
    }

    public void setCountIngredient(String countIngredient) {
        this.countIngredient = countIngredient;
    }

    public String getUnitIngredient() {
        return unitIngredient;
    }

    public void setUnitIngredient(String unitIngredient) {
        this.unitIngredient = unitIngredient;
    }
}
