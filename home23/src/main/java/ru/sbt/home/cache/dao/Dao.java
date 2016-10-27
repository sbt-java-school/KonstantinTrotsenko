package ru.sbt.home.cache.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO для работы сохранения результатов вычислений кэшируемых методов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Dao {
    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(Dao.class);
    private static final String DB = "jdbc:h2:C:/Users/Airo/IdeaProjects/SbtHome/home23/src/database/app";

    public Dao() {
        jdbcTemplate = new JdbcTemplate(DB);
    }

    /**
     * Метод для получения результата вычисления метода
     *
     * @param method имя метода
     * @param arguments аргументы метода
     * @return результат вычисления метода
     */
    public Object getResult(Object method, Object arguments) {
        LOGGER.info("****************** Get start");
        final Object[] object = new Object[1];
        return jdbcTemplate.execute(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select results from method " +
                            "where method = ? and arguments = ?");
            ) {
                statement.setObject(1, objectToByteArray(method));
                statement.setObject(2, objectToByteArray(arguments));
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                }
                try (ByteArrayInputStream bais = new ByteArrayInputStream(resultSet.getBytes("results"));
                     ObjectInputStream ins = new ObjectInputStream(bais)) {
                    object[0] = ins.readObject();
                } catch (Exception e) {
                    LOGGER.info("getResult", e);
                }
                LOGGER.info("****************** Get end. result: " + object);
                return object[0];
            }
        });
    }

    /**
     * Метод для записи имени метода, его аргументов и результат вычисления в DB
     *
     * @param method имя метода
     * @param arguments аргументы метода
     * @param results результат вычисления метода
     * @return true если запись прошла успешно
     */
    public boolean saveObject(Object method, Object arguments, Object results) {
        LOGGER.info("****************** Save start");
        return jdbcTemplate.execute(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into method (method, arguments, results)" +
                    "values (?, ?, ?)");
            statement.setObject(1, objectToByteArray(method));
            statement.setObject(2, objectToByteArray(arguments));
            statement.setObject(3, objectToByteArray(results));
            int result = statement.executeUpdate();
            LOGGER.info("****************** Save end. result: " + result);
            return result == 1;
        });
    }

    /**
     * Метод для серилизации объектов
     * @param object объект для серилизации
     * @return массив байтов
     */
    private static byte[] objectToByteArray(Object object) {
        byte[] data = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            oos.flush();
            data = bos.toByteArray();
        } catch (Exception e) {
            LOGGER.info("Exception in objectToByteArray", e);
        }
        return data;
    }
}
