package ru.sbt.home.view.base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Базовый класс для создания окон, с вынесенными общими методами
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public abstract class BaseController extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * Метод для создаяния нового окна
     * @param fxml
     * @param title
     */
    protected void createWindow(String fxml, String title) {
        try {
            SpringFxmlLoader loader = new SpringFxmlLoader();
            Stage primaryStage = new Stage();
            Parent root = (Parent) loader.load(getClass().getResource(fxml));
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (RuntimeException e) {
            LOGGER.error("Creation new window error",e);
        }
    }

    /**
     * Метод для вывода сообщения в новом окне
     *
     * @param message
     * @throws IOException
     */
    protected void message(String message){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText("Внимание!");
        dialog.setContentText(message);
        dialog.setResizable(true);
        dialog.getDialogPane().setPrefSize(300, 100);
        dialog.showAndWait();
    }
}
