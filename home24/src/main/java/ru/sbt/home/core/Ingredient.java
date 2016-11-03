package ru.sbt.home.core;

/**
 * Класс для создания объектов игредиентов (таблица Ingredients)
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Ingredient {
    private Integer id;
    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
