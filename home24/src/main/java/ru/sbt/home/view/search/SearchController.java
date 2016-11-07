package ru.sbt.home.view.search;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.home.dto.Recipe;
import ru.sbt.home.services.RecipeService;
import ru.sbt.home.view.base.BaseController;
import ru.sbt.home.view.base.SpringFxmlLoader;

import java.io.IOException;

/**
 * Окно для поиска рецепта
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public class SearchController extends BaseController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<Recipe> tableRecipes;
    @FXML
    private TableColumn<Recipe, Integer> id;
    @FXML
    private TableColumn<Recipe, String> recipe;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    private RecipeService recipeService;
    private ObservableList<Recipe> recipeData = FXCollections.observableArrayList();
    private String searchRow;
    private Integer recipeId;

    @Autowired
    public SearchController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    @FXML
    private void initialize() {
        updateAfterSearch("");
    }

    /**
     * Метод обновляет таблицу поле поиска
     *
     * @param recipeName название рецепта
     */
    private void updateAfterSearch(String recipeName) {
        recipeData.clear();
        recipeData.addAll(recipeService.findRecipesByName(recipeName));
        // устанавливаем тип и значение которое должно хранится в колонке
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        recipe.setCellValueFactory(new PropertyValueFactory<>("recipe"));
        // заполняем таблицу данными
        tableRecipes.setItems(recipeData);
    }

    /**
     * Обработчик кнопки "Поиск"
     */
    public void search(ActionEvent event) throws IOException {
        searchRow = txtSearch.getText().trim();
        updateAfterSearch(searchRow);
    }

    /**
     * Обработчик кнопки "Удалить"
     */
    public void delete(ActionEvent event) throws IOException {
        if (!txtId.getText().trim().equals("")) {
            recipeId = Integer.valueOf(txtId.getText());
            if (recipeService.containsRecipe(recipeId)) {
                recipeService.deleteRecipe(recipeId);
                searchRow = txtSearch.getText().trim();
                updateAfterSearch(searchRow);
            } else {
                message("Рецепта с таким id нет в выборке");
            }
        } else {
            message("Введите id");
        }
    }

    /**
     * Обработчик кнопки "Выбрать"
     */
    public void choice(ActionEvent event) throws IOException {
        if (!txtId.getText().trim().equals("")) {
            recipeId = Integer.valueOf(txtId.getText().trim());
            if (recipeService.containsRecipe(recipeId)) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                SpringFxmlLoader loader = new SpringFxmlLoader();
                Parent root = loader.load(getClass().getResource("/SearchRedactor.fxml"));
                SearchControllerRedactor controller = (SearchControllerRedactor) loader.getController();
                controller.setValue(recipeId);
                Stage stage = new Stage();
                stage.setTitle("Рецепт");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                message("Рецепта с таким id нет в выборке");
            }
        } else {
            message("Введите id");
        }
    }

    /**
     * Обработчик кнопки "Назад"
     */
    public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        createWindow("/MainController.fxml", "Книга рецептов");
    }
}
