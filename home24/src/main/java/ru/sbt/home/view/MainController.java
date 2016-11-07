package ru.sbt.home.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.sbt.home.view.base.BaseController;

import java.io.IOException;

/**
 * Главный контроллер
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public class MainController extends BaseController {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainController.fxml"));
        primaryStage.setTitle("Главная");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Обработчик кнопки Поиск
     */
    public void search(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        createWindow("/Search.fxml", "Поиск рецептов");
    }

    /**
     * Обработчик кнопки Создать
     */
    public void create(ActionEvent event) throws IOException {
        createWindow("/Create.fxml", "Новый рецепт");
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Обработчик кнопки Выход
     */
    public void exit(ActionEvent event) throws IOException {
        Platform.exit();
        System.exit(0);
    }
}
