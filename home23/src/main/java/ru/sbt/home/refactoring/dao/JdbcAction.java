package ru.sbt.home.refactoring.dao;

import java.sql.Connection;

/**
 * Интерфейс для передачи запросов в виде лямбды
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@FunctionalInterface
interface JdbcAction<T> {
    T execute(Connection connection) throws Exception;
}
