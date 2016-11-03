package ru.sbt.home.view.createbranch;

import javafx.application.Application;
import javafx.application.Platform;
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
import ru.sbt.home.view.searchbranch.SearchControllerRedactor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Окно для создаания новой записи в книге рецептов
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class CreateController extends Application {

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
    private RadioButton rbtnDelete;
    @FXML
    private javafx.scene.control.Button btnClose;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateController.class);
    private ObservableList<CookbookSheetSimple> cookbookData = FXCollections.observableArrayList();
    private List<CookbookSheetSimple> cookbookSheetSimple = new ArrayList<>();
    private String recipeName;
    private String recipeDescription;
    private static Integer ID = -1;

    private AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    private Dao dao = context.getBean(Dao.class);

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
        if (cookbookSheetSimple.size() != 0) {
            for (CookbookSheetSimple sheetSimple : cookbookSheetSimple) {
                cookbookData.add(sheetSimple);
            }
        }
    }

    /**
     * Метод для генерации внутреннего id
     * @return id
     */
    private Integer getId() {
        return ++ID;
    }

    /**
     * Метод проверяет налицие игредиента с данным id уже в списке
     * @param id
     * @return i положение в cookbookData
     */
    private Integer containId(Integer id) {
        for (int i = 0; i < cookbookData.size(); i++) {
            if (cookbookData.get(i).getIngredientId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Метод проверяет дублирование ингредиентов
     * @param ingredientName
     * @return i положение в cookbookData
     */
    private Integer sameIngredient(String ingredientName) {
        for (int i = 0; i < cookbookData.size(); i++) {
            if (cookbookData.get(i).getIngredientName().equals(ingredientName)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

    /**
     * Обработчик кнопки "Выполнить"
     * @param event
     * @throws IOException
     */
    public void execute(ActionEvent event) throws IOException {
        if (rbtnDelete.isSelected()) {
            if (!txtId.getText().trim().equals("")) {
                if (cookbookSheetSimple.size() != 0) {
                    Integer ingredientId = Integer.valueOf(txtId.getText().trim());
                    Integer containId = containId(ingredientId);
                    if (containId != null) {
                        cookbookSheetSimple.remove((int) containId);
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
            if (!txtIngredient.getText().trim().equals("") || !txtCount.getText().trim().equals("") ||
                    txtUnit.getText().trim().equals("")) {
                String ingredientName = txtIngredient.getText().trim();
                String ingredientCount = txtCount.getText().trim();
                String ingredientUnit = txtUnit.getText().trim();
                if (cookbookSheetSimple.size() != 0) {
                    Integer sameIngredient = sameIngredient(ingredientName);
                    if (sameIngredient != null) {
                        message("Такой инредиент уже у вас есть, обновите его");
                    } else {
                        cookbookSheetSimple.add(new CookbookSheetSimple(getId(), ingredientName, ingredientCount, ingredientUnit));
                        initialize();
                    }
                } else {
                    cookbookSheetSimple.add(new CookbookSheetSimple(getId(), ingredientName, ingredientCount, ingredientUnit));
                    initialize();
                }
            } else {
                message("Заполните форму");
            }
        }
        if (rbtnUpdateIngredient.isSelected()) {
            if (!txtId.getText().trim().equals("")) {
                Integer ingredientId = Integer.valueOf(txtId.getText().trim());
                String ingredientName = txtIngredient.getText().trim();
                String ingredientCount = txtCount.getText().trim();
                String ingredientUnit = txtUnit.getText().trim();
                if (cookbookSheetSimple.size() != 0) {
                    Integer containId = containId(ingredientId);
                    if (containId != null) {
                        Integer sameIngredient = sameIngredient(ingredientName);
                        if (sameIngredient != null) {
                            message("Такой инредиент уже у вас есть, обновите его");
                        } else {
                            cookbookSheetSimple.get(containId).setIngredientName(ingredientName);
                            cookbookSheetSimple.get(containId).setCountIngredient(ingredientCount);
                            cookbookSheetSimple.get(containId).setUnitIngredient(ingredientUnit);
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
     * @param event
     * @throws IOException
     */
    public void save(ActionEvent event) throws IOException {
        recipeName = txtName.getText().trim();
        recipeDescription = txtDescription.getText().trim();
        if (!recipeName.equals("") || !recipeDescription.equals("") || cookbookSheetSimple.size() != 0) {
            List<CookbookSheetSimple> realCookbookSheetSimple = checkRealIngredientId(cookbookSheetSimple);
            Integer recipeId = dao.createNewRecipe(recipeName, recipeDescription);
            for (CookbookSheetSimple sheetSimple : realCookbookSheetSimple) {
                dao.createNewIngredientToCookbook(recipeId, sheetSimple.getIngredientId(),
                        sheetSimple.getCountIngredient(), sheetSimple.getUnitIngredient());
            }
            LOGGER.info("Save!");
        } else {
            message("Не все поля заполнены");
        }
    }

    /**
     * Метод ищет введенные пользователем ингредиенты в базе данных, если такие уже есть, он присваивает их id - вновь
     * соданным, если нет, добавляет их и присваивает новые id
     * @param cookbookSheetSimple
     * @return
     */
    private List<CookbookSheetSimple> checkRealIngredientId(List<CookbookSheetSimple> cookbookSheetSimple) {
        for (CookbookSheetSimple sheetSimple : cookbookSheetSimple) {
            Integer realId = dao.findIngredientByName(sheetSimple.getIngredientName());
            if (realId != null) {
                sheetSimple.setIngredientId(realId);
            } else{
                Integer newRealId = dao.createNewIngredient(sheetSimple.getIngredientName());
                sheetSimple.setIngredientId(newRealId);
            }
        }
        return cookbookSheetSimple;
    }

    /**
     * Обработчик кнопки "Закрыть"
     */
    @FXML
    private void close(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Обработчик RadioButton Добавить новый ингредиент
     *
     * @param event
     * @throws IOException
     */
    public void addNewIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{true, false, false, false, true, true, true});
    }

    /**
     * Обработчик RadioButton Обновить ингредиент
     *
     * @param event
     * @throws IOException
     */
    public void updateIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, true, false, true, true, true, true});
    }

    /**
     * Обработчик RadioButton Удалить
     *
     * @param event
     * @throws IOException
     */
    public void deleteIngredient(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, true, true, false, false, false});
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
