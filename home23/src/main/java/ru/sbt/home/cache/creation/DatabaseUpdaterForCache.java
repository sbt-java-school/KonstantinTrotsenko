package ru.sbt.home.cache.creation;

import ru.sbt.home.refactoring.creation.DatabaseUpdater;

/**
 * Класс для создания базы данных
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class DatabaseUpdaterForCache {

    public static void main(String[] args) throws Exception {
        new DatabaseUpdater().executeScript("C:/Users/Airo/IdeaProjects/SbtHome/home23/src/main/sql/method.sql");
    }
}
