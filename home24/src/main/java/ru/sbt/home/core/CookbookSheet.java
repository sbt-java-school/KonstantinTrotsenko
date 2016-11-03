package ru.sbt.home.core;

import java.util.List;

/**
 * Класс для создания объектов для книги рецептов (таблица Cookbook)
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class CookbookSheet {
    private Recipe recipe;
    private List<Ingredient> ingredients;
    private List<Double> counts;
    private List<String> units;

    public CookbookSheet() {
    }

    public CookbookSheet(Recipe recipe, List<Ingredient> ingredients, List<Double> counts, List<String> units) {
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.counts = counts;
        this.units = units;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Double> getCounts() {
        return counts;
    }

    public void setCounts(List<Double> counts) {
        this.counts = counts;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }
}
