package ru.sbt.home.dto;

/**
 * Класс для создания объектов игредиентов (таблица Ingredients)
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Ingredient {
    private Integer id;
    private String name;
    private String countIngredient;
    private String unitIngredient;

    /*public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(Integer id, String name) {
        this.id = id;
        this.name = name;
    }*/

    public Ingredient(Integer id, String name, String countIngredient, String unitIngredient) {
        this.id = id;
        this.name = name;
        this.countIngredient = countIngredient;
        this.unitIngredient = unitIngredient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
