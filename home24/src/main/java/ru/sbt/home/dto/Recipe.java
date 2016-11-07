package ru.sbt.home.dto;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe1 = (Recipe) o;

        if (!recipe.equals(recipe1.recipe)) return false;
        return description != null ? description.equals(recipe1.description) : recipe1.description == null;

    }

    @Override
    public int hashCode() {
        int result = recipe.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
