package ru.sbt.home.view.create;

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
import ru.sbt.home.view.base.BaseController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Окно для создаания новой записи в книге рецептов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
@Component
public class CreateController extends BaseController {
    @FXML
    private Label lblId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblCount;
    @FXML
    private Label lblUnit;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateController.class);
    private IngredientService ingredientService;
    private ObservableList<Ingredient> ingredientData = FXCollections.observableArrayList();
    private List<Ingredient> ingredients = new ArrayList<>();
    private String recipeName;
    private String recipeDescription;
    private Integer ingredientId;
    private String ingredientName;
    private String ingredientCount;
    private String ingredientUnit;
    private static Integer ID = -1;

    @Autowired
    public CreateController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    private void initialize() {
        // подготавливаем данные для таблицы
        ingredientData.clear();
        if (ingredients.size() != 0) {
            ingredientData.addAll(ingredients);
            // устанавливаем тип и значение которое должно хранится в колонке
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            countColumn.setCellValueFactory(new PropertyValueFactory<>("countIngredient"));
            unitColumn.setCellValueFactory(new PropertyValueFactory<>("unitIngredient"));
            // заполняем таблицу данными
            tableRecipe.setItems(ingredientData);
        }
    }

    /**
     * Метод для генерации внутреннего id
     *
     * @return id
     */
    private Integer getId() {
        return ++ID;
    }

    /**
     * Метод проверяет налицие игредиента с данным id уже в списке
     *
     * @param id ингредиент id
     * @return i положение в ingredientData
     */
    private Integer containId(Integer id) {
        for (int i = 0; i < ingredientData.size(); i++) {
            if (ingredientData.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Метод проверяет дублирование ингредиентов
     *
     * @param ingredientName имя ингредиента
     * @return i положение в ingredientData
     */
    private Integer sameIngredient(String ingredientName) {
        for (int i = 0; i < ingredientData.size(); i++) {
            if (ingredientData.get(i).getName().equals(ingredientName)) {
                return i;
            }
        }
        return null;
    }

    /**
     * Обработчик кнопки "Выполнить"
     */
    public void execute(ActionEvent event) throws IOException {
        if (rbtnDelete.isSelected()) {
            if (!txtId.getText().trim().equals("")) {
                if (ingredients.size() != 0) {
                    ingredientId = Integer.valueOf(txtId.getText().trim());
                    Integer containId = containId(ingredientId);
                    if (containId != null) {
                        ingredients.remove((int) containId);
                        initialize();
                    } else {
                        message("В списке нет данного id");
                    }
                } else {
                    message("Список пуст");
                }
            } else {
                message("Заполните форму");
            }
        }
        if (rbtnAddNew.isSelected()) {
            ingredientName = txtIngredient.getText().trim();
            ingredientCount = txtCount.getText().trim();
            ingredientUnit = txtUnit.getText().trim();
            if (!ingredientName.equals("") && !ingredientCount.equals("") && !ingredientUnit.equals("")) {
                if (ingredients.size() != 0) {
                    Integer sameIngredient = sameIngredient(ingredientName);
                    if (sameIngredient != null) {
                        message("Такой инредиент уже у вас есть, обновите его");
                    } else {
                        ingredients.add(new Ingredient(getId(), ingredientName, ingredientCount, ingredientUnit));
                        initialize();
                    }
                } else {
                    ingredients.add(new Ingredient(getId(), ingredientName, ingredientCount, ingredientUnit));
                    initialize();
                }
            } else {
                message("Заполните все поля");
            }
        }
        if (rbtnUpdateIngredient.isSelected()) {
            if (!txtId.getText().trim().equals("")) {
                ingredientId = Integer.valueOf(txtId.getText().trim());
                ingredientName = txtIngredient.getText().trim();
                ingredientCount = txtCount.getText().trim();
                ingredientUnit = txtUnit.getText().trim();
                if (ingredients.size() != 0) {
                    Integer containId = containId(ingredientId);
                    if (containId != null) {
                        Integer sameIngredient = sameIngredient(ingredientName);
                        if (sameIngredient != null) {
                            if (containId == sameIngredient) {
                                ingredients.get(containId).setCountIngredient(ingredientCount);
                                ingredients.get(containId).setUnitIngredient(ingredientUnit);
                                initialize();
                            } else {
                                message("Такой ингредиент у вас уже есть");
                            }
                        } else {
                            ingredients.get(containId).setName(ingredientName);
                            ingredients.get(containId).setCountIngredient(ingredientCount);
                            ingredients.get(containId).setUnitIngredient(ingredientUnit);
                            initialize();
                        }
                    } else {
                        message("В списке нет данного id");
                    }
                } else {
                    message("Список пуст");
                }
            } else {
                message("Заполните форму");
            }
        }
    }

    /**
     * Обработчик кнопки "Сохранить"
     */
    public void save(ActionEvent event) throws IOException {
        recipeName = txtName.getText().trim();
        recipeDescription = txtDescription.getText().trim();
        if (!recipeName.equals("") && !recipeDescription.equals("") && ingredients.size() != 0) {
            boolean save = ingredientService.saveReсipeWithIngredients(new Recipe(recipeName, recipeDescription), ingredients);
            if (save) {
                LOGGER.info("Save success!");
                message("Рецепт сохранен");
                back(event);
            } else {
                LOGGER.info("Smt wrong!");
            }
        } else {
            message("Не все поля заполнены");
        }
    }

    /**
     * Обработчик кнопки "Назад"
     */
    public void back(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        createWindow("/MainController.fxml", "Книга рецептов");
    }

    /**
     * Обработчик RadioButton Добавить новый ингредиент
     */
    public void addNewIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{true, false, false, false, true, true, true});
    }

    /**
     * Обработчик RadioButton Обновить ингредиент
     */
    public void updateIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, true, false, true, true, true, true});
    }

    /**
     * Обработчик RadioButton Удалить
     */
    public void deleteIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, true, true, false, false, false});
    }

    /**
     * Контроллер видимости в зависимости от RadioButtons
     *
     * @param array с необходимыми настройками
     */
    private void radioButtonController(boolean[] array) {
        rbtnAddNew.setSelected(array[0]);
        rbtnUpdateIngredient.setSelected(array[1]);
        rbtnDelete.setSelected(array[2]);
        txtId.setVisible(array[3]);
        lblId.setVisible(array[3]);
        txtIngredient.setVisible(array[4]);
        lblName.setVisible(array[4]);
        txtCount.setVisible(array[5]);
        lblCount.setVisible(array[5]);
        txtUnit.setVisible(array[6]);
        lblUnit.setVisible(array[6]);
    }
}
