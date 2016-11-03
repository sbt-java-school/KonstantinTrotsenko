package ru.sbt.home.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Класс для вывода сообщения об ошибках
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Message implements Initializable {
    @FXML
    private Label lblMessage;

    /**
     * Метод для вывода сообщений
     * @param message сообщение
     */
    public void setResult(String message) {
        lblMessage.setText(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
