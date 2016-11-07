package ru.sbt.home.view.search;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.home.dto.Ingredient;
import ru.sbt.home.dto.Recipe;
import ru.sbt.home.services.IngredientService;
import ru.sbt.home.services.RecipeService;
import ru.sbt.home.view.base.BaseController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Окно для редактирования рецепта
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public class SearchControllerRedactor extends BaseController {
    @FXML
    private Label lblRecipeDescription;
    @FXML
    private Label lblRecipeName;
    @FXML
    private Label lblId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblCount;
    @FXML
    private Label lblUnit;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIngredient;
    @FXML
    private TextField txtCount;
    @FXML
    private TextField txtUnit;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDescription;

    @FXML
    private RadioButton rbtnAddNew;
    @FXML
    private RadioButton rbtnUpdateIngredient;
    @FXML
    private RadioButton rbtnUpdateDescription;
    @FXML
    private RadioButton rbtnUpdateName;
    @FXML
    private RadioButton rbtnDelete;

    @FXML
    private TableView<Ingredient> tableRecipe;
    @FXML
    private TableColumn<Ingredient, Integer> idColumn;
    @FXML
    private TableColumn<Ingredient, String> ingredientColumn;
    @FXML
    private TableColumn<Ingredient, String> countColumn;
    @FXML
    private TableColumn<Ingredient, String> unitColumn;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchControllerRedactor.class);
    private IngredientService ingredientService;
    private RecipeService recipeService;
    private ObservableList<Ingredient> recipeData = FXCollections.observableArrayList();
    private Map<Recipe, List<Ingredient>> recipeWithIngredients;
    private List<Ingredient> ingredients;
    private Recipe recipe;
    private Integer id;

    @Autowired
    public SearchControllerRedactor(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    /**
     * Метод для передачи id из окна поиска
     *
     * @param idFrom id из окна поиска
     */
    void setValue(Integer idFrom) {
        id = idFrom;
        initialize();
    }

    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("countIngredient"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unitIngredient"));
        // заполняем таблицу данными
        tableRecipe.setItems(recipeData);
    }

    // подготавливаем данные для таблицы
    private void initData() {
        recipeData.clear();
        recipeWithIngredients = ingredientService.getRecipeWithIngredientsByRecipeId(id);
        Map.Entry<Recipe, List<Ingredient>> entry = recipeWithIngredients.entrySet().iterator().next();
        recipe = entry.getKey();
        ingredients = entry.getValue();
        if (recipe != null && ingredients != null) {
            recipeData.addAll(ingredients);
            lblRecipeName.setText(recipe.getRecipe());
            lblRecipeDescription.setText(recipe.getDescription());
        }
    }

    /**
     * Обработчик кнопки "Выполнить"
     */
    public void start(ActionEvent event) throws IOException {
        if (rbtnUpdateName.isSelected()) {
            String newName = txtName.getText().trim();
            if (!newName.equals("")) {
                recipeService.updateRecipeName(id, newName);
                initialize();
            } else {
                message("Заполните поле");
            }
        }
        if (rbtnUpdateDescription.isSelected()) {
            String newDescription = txtDescription.getText().trim();
            if (!newDescription.equals("")) {
                recipeService.updateRecipeDescription(id, newDescription);
                initialize();
            } else {
                message("Заполните поле");
            }

        }
        if (rbtnDelete.isSelected()) {
            if (ingredients.size() > 1) {
                String ingredientId = txtId.getText().trim();
                if (!ingredientId.equals("")) {
                    if (ingredientHasAlready(Integer.parseInt(ingredientId))) {
                        ingredientService.deleteIngredientFromCookbook(id, ingredientId);
                        initialize();
                    } else {
                        message("В данном рецепте нет ингредиента с таким id");
                    }
                } else {
                    message("Заполните поле");
                }
            } else {
                message("В рецепте должен быть хоть один ингредиент");
            }
        }
        if (rbtnAddNew.isSelected()) {
            String ingredientName = txtIngredient.getText().trim();
            String ingredientCount = txtCount.getText().trim();
            String ingredientUnit = txtUnit.getText().trim();
            if (!ingredientName.equals("") && !ingredientCount.equals("") && !ingredientUnit.equals("")) {
                Integer sameIngredientInDbId = ingredientService.findIngredientByName(ingredientName);
                if (sameIngredientInDbId == null) {
                    ingredientService.createNewIngredientToCookbookNewId(id, ingredientName, ingredientCount, ingredientUnit);
                    initialize();
                } else {
                    if (!ingredientHasAlready(sameIngredientInDbId)) {
                        ingredientService.createNewIngredientToCookbook(id, sameIngredientInDbId, ingredientCount, ingredientUnit);
                        initialize();

                    } else {
                        message("Такой инредиент уже у вас есть, обновите его");
                    }
                }
            } else {
                message("Заполните все поля");
            }
        }
        if (rbtnUpdateIngredient.isSelected()) {
            String ingredientId = txtId.getText().trim();
            String ingredientCount = txtCount.getText().trim();
            String ingredientUnit = txtUnit.getText().trim();
            if (!ingredientId.equals("") && !ingredientCount.equals("") && !ingredientUnit.equals("")) {
                if (ingredientHasAlready(Integer.parseInt(ingredientId))) {
                    ingredientService.updateIngredientInCookbook(ingredientId, ingredientCount, ingredientUnit);
                    initialize();
                } else {
                    message("В данном рецепте нет ингредиента с таким id");
                }
            } else {
                message("Заполните все поля");
            }
        }
    }

    /**
     * Обработчик кнопки "Назад"
     */
    public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        createWindow("/Search.fxml", "Поиск рецептов");
    }

    /**
     * Метод проверяет наличие инредиента в списке
     *
     * @param sameIngredientInDbId id ингредиента
     * @return true если уже имееться
     */
    private boolean ingredientHasAlready(Integer sameIngredientInDbId) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId() == sameIngredientInDbId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Обработчик RadioButton Добавить ингредиент
     */
    public void addIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{true, false, false, false, false, false, false, false, true, true, true,
                false, false});
    }

    /**
     * Обработчик RadioButton Добавить новый ингредиент
     */
    public void addNewIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, true, false, false, false, false, false, true, true, true, false,
                false, false});
    }

    /**
     * Обработчик RadioButton Обновить ингредиент
     */
    public void updateIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, true, false, false, false, true, false, true, true, false,
                false, false});
    }

    /**
     * Обработчик RadioButton Обновить описание
     */
    public void updateDescription(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, false, true, false, false, false, false, false, false, false,
                true, false});
    }

    /**
     * Обработчик RadioButton Обновить название
     */
    public void updateName(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, false, false, true, false, false, false, false, false, false,
                false, true});
    }

    /**
     * Обработчик RadioButton Удалить
     */
    public void deleteIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, false, false, false, true, true, false, false, false, false,
                false, false});
    }

    /**
     * Контроллер видимости в зависимости от RadioButtons
     *
     * @param array с необходимыми настройками
     */
    private void radioButtonController(boolean[] array) {
        rbtnAddNew.setSelected(array[1]);
        rbtnUpdateIngredient.setSelected(array[2]);
        rbtnUpdateDescription.setSelected(array[3]);
        rbtnUpdateName.setSelected(array[4]);
        rbtnDelete.setSelected(array[5]);
        txtId.setVisible(array[6]);
        lblId.setVisible(array[6]);
        txtIngredient.setVisible(array[7]);
        lblName.setVisible(array[7]);
        txtCount.setVisible(array[8]);
        lblCount.setVisible(array[8]);
        txtUnit.setVisible(array[9]);
        lblUnit.setVisible(array[9]);
        txtDescription.setVisible(array[11]);
        lblDescription.setVisible(array[11]);
        txtName.setVisible(array[12]);
        lblTitle.setVisible(array[12]);
    }
}