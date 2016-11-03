package ru.sbt.home.view.searchbranch;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.home.ApplicationConfiguration;
import ru.sbt.home.core.CookbookSheetSimple;
import ru.sbt.home.dao.Dao;
import ru.sbt.home.view.Message;

import java.io.IOException;
import java.util.List;

/**
 * Окно для редактирования рецепта
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class SearchControllerRedactor extends Application {
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
    private RadioButton rbtnAdd;
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
    private Button btnStart;

    @FXML
    private ComboBox cbIngredients;

    @FXML
    private TableView<CookbookSheetSimple> tableRecipe;
    @FXML
    private TableColumn<CookbookSheetSimple, Integer> idColumn;
    @FXML
    private TableColumn<CookbookSheetSimple, String> ingredientColumn;
    @FXML
    private TableColumn<CookbookSheetSimple, String> countColumn;
    @FXML
    private TableColumn<CookbookSheetSimple, String> unitColumn;


    private static final Logger LOGGER = LoggerFactory.getLogger(SearchControllerRedactor.class);
    private ObservableList<CookbookSheetSimple> cookbookData = FXCollections.observableArrayList();
    private List<CookbookSheetSimple> cookbookSheetSimple;
    private Integer id;
    private String recipeName;
    private String recipeDescription;

    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    private Dao dao = context.getBean(Dao.class);

    public void setValue(Integer idFrom) {
        id = idFrom;
        initialize();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientId"));
        ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("countIngredient"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unitIngredient"));
        // заполняем таблицу данными
        tableRecipe.setItems(cookbookData);
    }
    // подготавливаем данные для таблицы
    private void initData() {
        cookbookData.clear();
        cookbookSheetSimple = dao.getCookbookSheetWithRecipeId(id);
        if (cookbookSheetSimple != null) {
            for (CookbookSheetSimple sheetSimple : cookbookSheetSimple) {
                cookbookData.add(sheetSimple);
            }
            recipeName = cookbookSheetSimple.get(0).getRecipeName();
            recipeDescription = cookbookSheetSimple.get(0).getRecipeDescription();
            lblRecipeName.setText(recipeName);
            lblRecipeDescription.setText(recipeDescription);
        }
    }

    /**
     * Обработчик кнопки "Выполнить"
     *
     * @param event
     * @throws IOException
     */
    public void start(ActionEvent event) throws IOException {
        if (rbtnUpdateName.isSelected()) {
            String newName = txtName.getText().trim();
            dao.updateRecipeName(id, newName);
            initialize();
        }
        if (rbtnUpdateDescription.isSelected()) {
            String newDescription = txtDescription.getText().trim();
            dao.updateRecipeDescription(id, newDescription);
            initialize();
        }
        if (rbtnDelete.isSelected()) {
            if (cookbookSheetSimple.size() > 1) {
                String ingredientId = txtId.getText().trim();
                dao.deleteIngredientFromCookbook(id, ingredientId);
                initialize();
            } else {
                message("В рецепте должен быть хоть один ингредиент");
            }
        }
        if (rbtnAddNew.isSelected()) {
            String ingredientName = txtIngredient.getText().trim();
            String ingredientCount = txtCount.getText().trim();
            String ingredientUnit = txtUnit.getText().trim();
            Integer sameIngredientInDbId = dao.findIngredientByName(ingredientName);
            if (sameIngredientInDbId == null) {
                Integer newIngredientId = dao.createNewIngredient(ingredientName);
                dao.createNewIngredientToCookbook(id, newIngredientId, ingredientCount, ingredientUnit);
                initialize();
            } else {
                if (!ingredientHasAlready(sameIngredientInDbId)) {
                    dao.createNewIngredientToCookbook(id, sameIngredientInDbId, ingredientCount, ingredientUnit);
                    initialize();

                } else {
                    message("Такой инредиент уже у вас есть, обновите его");
                }
            }
        }
        if (rbtnUpdateIngredient.isSelected()) {
            String ingredientId = txtId.getText().trim();
            String ingredientCount = txtCount.getText().trim();
            String ingredientUnit = txtUnit.getText().trim();
            dao.updateIngredientInCookbook(ingredientId, ingredientCount, ingredientUnit);
            initialize();
        }
    }

    /**
     * Метод проверяет наличие инрелиента в списке
     * @param sameIngredientInDbId
     * @return
     */
    private boolean ingredientHasAlready(Integer sameIngredientInDbId) {
        for (CookbookSheetSimple sheetSimple : cookbookSheetSimple) {
            if (sheetSimple.getIngredientId() == sameIngredientInDbId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Обработчик RadioButton Добавить ингредиент
     *
     * @param event
     * @throws IOException
     */
    public void addIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{true, false, false, false, false, false, false, false, true, true, true,
                false, false});
    }

    /**
     * Обработчик RadioButton Добавить новый ингредиент
     *
     * @param event
     * @throws IOException
     */
    public void addNewIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, true, false, false, false, false, false, true, true, true, false,
                false, false});
    }

    /**
     * Обработчик RadioButton Обновить ингредиент
     *
     * @param event
     * @throws IOException
     */
    public void updateIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, true, false, false, false, true, false, true, true, false,
                false, false});
    }

    /**
     * Обработчик RadioButton Обновить описание
     *
     * @param event
     * @throws IOException
     */
    public void updateDescription(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, false, true, false, false, false, false, false, false, false,
                true, false});
    }

    /**
     * Обработчик RadioButton Обновить название
     *
     * @param event
     * @throws IOException
     */
    public void updateName(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, false, false, true, false, false, false, false, false, false,
                false, true});
    }

    /**
     * Обработчик RadioButton Удалить
     *
     * @param event
     * @throws IOException
     */
    public void deleteIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, false, false, false, true, true, false, false, false, false,
                false, false});
    }

    /**
     * Метод для вывода сообщения в новом окне
     *
     * @param message
     * @throws IOException
     */
    private void message(String message) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/Message.fxml").openStream());
        Message resultController = loader.getController();
        resultController.setResult(message);
        primaryStage.setTitle("Warning!");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Контроллер видимости в зависимости от RadioButtons
     *
     * @param array с необходимыми настройками
     */
    private void radioButtonController(boolean[] array) {
        //rbtnAdd.setSelected(array[0]);
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
        //cbIngredients.setVisible(array[10]);
        txtDescription.setVisible(array[11]);
        lblDescription.setVisible(array[11]);
        txtName.setVisible(array[12]);
        lblTitle.setVisible(array[12]);
    }
}
