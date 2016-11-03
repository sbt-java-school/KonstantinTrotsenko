package ru.sbt.home.core;

/**
 * Класс для создания рецептов (таблица Recipes)
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Recipe {
    private Integer id;
    private String recipe;
    private String description;

    public Recipe() {
    }

    public Recipe(String recipe, String description) {
        this.recipe = recipe;
        this.description = description;
    }

    public Recipe(Integer id, String recipe, String description) {
        this.id = id;
        this.recipe = recipe;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
