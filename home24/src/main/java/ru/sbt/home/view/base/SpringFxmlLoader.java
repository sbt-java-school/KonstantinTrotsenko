package ru.sbt.home.view.base;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.home.configuration.ApplicationConfiguration;

import java.io.IOException;
import java.net.URL;

/**
 * Класс для загрузки Spring контроллеров
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class SpringFxmlLoader {

    private final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    private boolean loaded;
    private Object controller;

    public <T> T load(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            loader.setControllerFactory(applicationContext::getBean);
            T root = loader.load();
            controller = loader.getController();
            loaded = true;
            return root;
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public Object getController() {
        if (!loaded) {
            throw new IllegalStateException("Controller is only available after loading");
        }
        return controller;
    }
}
