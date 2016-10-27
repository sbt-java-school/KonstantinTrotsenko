package ru.sbt.home.cache.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для передачи запросов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class JdbcTemplate {
    private String url;

    public JdbcTemplate(String url) {
        this.url = url;
    }

    /**
     * Метод для выполения запросов на открытом соединении
     * @param action какое либо действие (запрос)
     * @return объект запроса
     * @see JdbcAction
     */
     <T> T execute(JdbcAction<T> action) {
        try (Connection connection = openConnection()) {
            return action.execute(connection);
        } catch (Exception e) {
            throw new IllegalStateException("Execute error", e);
        }
    }

    /**
     * Метод для получения соединения
     * @return connection
     * @throws SQLException
     */
    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
