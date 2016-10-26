package ru.sbt.home.refactoring.dao;

import ru.sbt.home.refactoring.core.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * DAO для работы с User
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Метод для получения пользователя по login
     * @param login
     * @return user
     */
    User findByLogin(String login) {
        return jdbcTemplate.execute(connection -> {
            try (PreparedStatement statement = connection.prepareStatement(
                    "select id, login, password_hash from user " +
                            "where login = ?");
            ) {
                statement.setString(1, login);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                }
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );
                return user;
            }
        });
    }

    /**
     * Метод для записи нового пользователя в DB
     * @param user
     * @return true если запись прошла успешно
     */
    boolean create(User user) {
        return jdbcTemplate.execute(connection -> {
            PreparedStatement statement = connection.prepareStatement("insert into user (login, password_hash)" +
                    "values (?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordMd5());
            int result = statement.executeUpdate();
            return result == 1;
        });
    }

    /**
     * Метод для обновления полей пользователя
     * @param user
     * @param id
     * @return true если обновление прошло успешно
     */
    boolean update(User user, Long id) {
        return jdbcTemplate.execute(connection -> {
            PreparedStatement statement = connection.prepareStatement("update user set login = ?,  password_hash = ? " +
                    "where id = ?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPasswordMd5());
            statement.setString(3, String.valueOf(id));
            int result = statement.executeUpdate();
            return result == 1;
        });
    }

    /**
     * Метод для удаления пользователя по его id
     * @param id
     * @return true если удаление прошло успешно
     */
    boolean delete(Long id) {
        return jdbcTemplate.execute(connection -> {
            PreparedStatement statement = connection.prepareStatement("delete from user where id = ?");
            statement.setString(1, String.valueOf(id));
            int result = statement.executeUpdate();
            return result == 1;
        });
    }

    /**
     * Метод для получения всех пользователей
     * @return list of uset
     */
    List<User> list() {
        return jdbcTemplate.execute(connection -> {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("select id, login, password_hash from user")
            ) {
                List<User> users = new ArrayList<User>();
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                    users.add(user);
                }
                return users;
            }
        });
    }
}
