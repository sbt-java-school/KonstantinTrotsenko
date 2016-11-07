package ru.sbt.home.creationdb;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sbt.home.configuration.ApplicationConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Класс для создания базы данных
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */

@Component
public class DatabaseUpdater {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUpdater.class);
    private JdbcTemplate jdbcTemplate;

    public DatabaseUpdater(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.getBean(DatabaseUpdater.class).executeScript("home24/src/main/sql/cookbook.sql");

    }

    private void executeScript(String fileName) {
        try {
            String sqlScript = FileUtils.readFileToString(new File(fileName));
            executeSql(sqlScript);
        } catch (IOException e) {
            LOGGER.info("Can't read file: " + fileName, e);
        }
    }

    private void executeSql(String sql) {
        jdbcTemplate.update(sql);
    }
}
