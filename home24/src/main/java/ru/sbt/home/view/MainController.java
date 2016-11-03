package ru.sbt.home.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Главный контроллер
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class MainController extends Application {
    @FXML
    private Label lblHello;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnExit;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainController.fxml"));
        primaryStage.setTitle("Рецепты");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Обработчик кнопки Поиск
     * @param event
     * @throws IOException
     */
    public void search(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Search.fxml"));
        primaryStage.setTitle("Рецепты");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Обработчик кнопки Создать
     * @param event
     * @throws IOException
     */
    public void create(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Create.fxml"));
        primaryStage.setTitle("Рецепты");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Обработчик кнопки Выход
     * @param event
     * @throws IOException
     */
    public void exit(ActionEvent event) throws IOException {
        Platform.exit();
        System.exit(0);
    }
}
