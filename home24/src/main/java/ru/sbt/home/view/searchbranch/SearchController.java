package ru.sbt.home.view.searchbranch;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.home.ApplicationConfiguration;
import ru.sbt.home.core.Recipe;
import ru.sbt.home.dao.Dao;

import java.io.IOException;
import java.util.List;

/**
 * Окно для поиска рецепта
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class SearchController extends Application {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Button btnChoice;
    @FXML
    private TableView<Recipe> tableRecipes;
    @FXML
    private TableColumn<Recipe, Integer> id;
    @FXML
    private TableColumn<Recipe, String> recipe;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    private ObservableList<Recipe> recipeData = FXCollections.observableArrayList();
    private List<Recipe> recipes;

    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    private Dao dao = context.getBean(Dao.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @FXML
    private void initialize() {
        updateAfterSearch("");
    }

    private void updateAfterSearch(String searchRow) {
        recipeData.clear();
        recipes = dao.getRecipesWithParam(searchRow);
        for (Recipe recipe : recipes) {
            recipeData.add(recipe);
        }
        // устанавливаем тип и значение которое должно хранится в колонке
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        recipe.setCellValueFactory(new PropertyValueFactory<>("recipe"));
        // заполняем таблицу данными
        tableRecipes.setItems(recipeData);
    }

    /**
     * Обработчик кнопки "Поиск"
     * @param event
     * @throws IOException
     */
    public void search(ActionEvent event) throws IOException {
        String searchRow = txtSearch.getText().trim();
        updateAfterSearch(searchRow);
    }

    /**
     * Обработчик кнопки "Удалить"
     * @param event
     * @throws IOException
     */
    public void delete(ActionEvent event) throws IOException {
        Integer recipeId = Integer.valueOf(txtId.getText());
        if (containsRecipe(recipeId)) {
            dao.deleteCookbookSheet(recipeId);
            dao.deleteRecipe(recipeId);
            String searchRow = txtSearch.getText().trim();
            updateAfterSearch(searchRow);
        }
    }

    /**
     * Обработчик кнопки "Выбрать"
     * @param event
     * @throws IOException
     */
    public void choice(ActionEvent event) throws IOException {
        Integer id = Integer.valueOf(txtId.getText());
        if (containsRecipe(id)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SearchRedactor.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            SearchControllerRedactor controller = fxmlLoader.<SearchControllerRedactor>getController();
            controller.setValue(id);
            Stage stage = new Stage();
            stage.setTitle("Рецепт");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        //((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Метод проверяет налицие рецепта в выборке
     * @param id
     * @return
     */
    private boolean containsRecipe(Integer id) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}
