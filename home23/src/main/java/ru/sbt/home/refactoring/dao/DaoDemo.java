package ru.sbt.home.refactoring.dao;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbt.home.refactoring.core.User;
import ru.sbt.home.refactoring.view.MainController;

import java.util.List;
import java.util.Optional;

/**
 * DAO для конкретной работы с User
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 * @see UserDao
 */
public class DaoDemo {

    private final UserDao userDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoDemo.class);
    private static final String DB = "jdbc:h2:./home23/src/database/app";

    public DaoDemo() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DB);
        userDao = new UserDao(jdbcTemplate);
    }

    public User findByLogin(String login) {
        User user = userDao.findByLogin(login);
        if (user != null) {
            LOGGER.info(user.toString());
        } else {
            LOGGER.info("User not found: " + login);
        }
        return user;
    }

    public void createUser(String login, String password) {
        LOGGER.info("****************** Create start");
        User user = new User();
        user.setLogin(login);
        String md5Hex = DigestUtils.md5Hex(password);
        user.setPasswordMd5(md5Hex);
        boolean result = userDao.create(user);
        LOGGER.info("****************** Create end. result: " + result);
    }

    public void updateUser(String login, String password, Long id) {
        LOGGER.info("****************** Update start");
        User user = new User();
        user.setLogin(login);
        String md5Hex = DigestUtils.md5Hex(password);
        user.setPasswordMd5(md5Hex);
        boolean result = userDao.update(user, id);
        LOGGER.info("****************** Update end. result: " + result);
    }

    public void deleteUser(Long id) {
        LOGGER.info("****************** Delete start");
        boolean result = userDao.delete(id);
        LOGGER.info("****************** Delete end. result: " + result);
    }

    public List<User> listUsers() {
        LOGGER.info("****************** List start");
        List<User> users = userDao.list();
        LOGGER.info("****************** List end");
        return users;
    }
}
