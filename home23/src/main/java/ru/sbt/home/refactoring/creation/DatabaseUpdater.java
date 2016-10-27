package ru.sbt.home.refactoring.creation;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для создания базы данных
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class DatabaseUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUpdater.class);

    public static void main(String[] args) throws Exception {
        new DatabaseUpdater().executeScript("C:/Users/Airo/IdeaProjects/SbtHome/home23/src/main/sql/user.sql");
    }

    public void executeScript(String fileName) {
        try {
            String sqlScript = FileUtils.readFileToString(new File(fileName));
            executeSql(sqlScript);
        } catch (SQLException e) {
            throw new IllegalArgumentException("Bad script: " + fileName, e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can't read file: " + fileName, e);
        }
    }

    private void executeSql(String sql) throws SQLException {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:h2:C:/Users/Airo/IdeaProjects/SbtHome/home23/src/database/app");
             Statement statement = connection.createStatement()) {
            boolean execute = statement.execute(sql);
            LOGGER.info("---------------------------------");
            LOGGER.info("State:" + execute);
            LOGGER.info("SQL: " + sql);
            LOGGER.info("---------------------------------");
        }
    }
}
