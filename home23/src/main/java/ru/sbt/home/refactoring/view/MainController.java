package ru.sbt.home.refactoring.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbt.home.refactoring.core.User;
import ru.sbt.home.refactoring.dao.DaoDemo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CrUD на Java FX
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class MainController extends Application {
    @FXML
    private Label lblId;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblLogin;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private RadioButton rbtnCreate;
    @FXML
    private RadioButton rbtnUpdate;
    @FXML
    private RadioButton rbtnDelete;
    @FXML
    private Button btnStart;
    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, Integer> idColumn;
    @FXML
    private TableColumn<User, String> loginColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private ObservableList<User> usersData = FXCollections.observableArrayList();
    private final DaoDemo daoDemo = new DaoDemo();
    private List<User> users;
    private Map<Long, String> usersCache;
    private String login;
    private String password;
    private Long id;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("User list");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void initialize() {
        initData();
        // устанавливаем тип и значение которое должно хранится в колонке
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passwordMd5"));
        // заполняем таблицу данными
        tableUsers.setItems(usersData);
    }

    // подготавливаем данные для таблицы
    private void initData() {
        usersData.clear();
        usersCache = new HashMap<>();
        users = daoDemo.listUsers();
        for (User user : users) {
            usersData.add(user);
            usersCache.put(user.getId(), user.getLogin());
        }
    }

    /**
     * Обработчик кнопки Start
     * @param event
     * @throws IOException
     */
    public void startMainController(ActionEvent event) throws IOException {
        if (rbtnCreate.isSelected()) {
            login = txtLogin.getText();
            password = txtPassword.getText();
            if (!usersCache.containsValue(login)){
                daoDemo.createUser(login, password);
                initialize();
            } else {
                message("Данный login уже существует!");
            }
        } else if (rbtnUpdate.isSelected()) {
            id = Long.valueOf(txtId.getText());
            login = txtLogin.getText();
            password = txtPassword.getText();
            if(usersCache.containsKey(id)&&!usersCache.containsValue(login)){
                daoDemo.updateUser(login, password, id);
                initialize();
            } else {
                message("User с данным id нет или повтор login!");
            }
        } else if (rbtnDelete.isSelected()){
            id = Long.valueOf(txtId.getText());
            if(usersCache.containsKey(id)){
                daoDemo.deleteUser(id);
                initialize();
            } else {
                message("User с данным id нет!");
            }
        }
    }

    /**
     * Обработчик RadioButton Create
     * @param event
     * @throws IOException
     */
    public void createUser(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{true, false, false, false, true, true});
    }

    /**
     * Обработчик RadioButton Update
     * @param event
     * @throws IOException
     */
    public void updateUser(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, true, false, true, true, true});
    }

    /**
     * Обработчик RadioButton Delete
     * @param event
     * @throws IOException
     */
    public void deleteUser(ActionEvent event) throws IOException {
        radioButtonController(new boolean[]{false, false, true, true, false, false});
    }

    /**
     * Метод для вывода сообщения в новом окне
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
     * Контроллер RadioButtons
     * @param array с необходимыми настройками
     */
    private void radioButtonController(boolean[] array) {
        rbtnCreate.setSelected(array[0]);
        rbtnUpdate.setSelected(array[1]);
        rbtnDelete.setSelected(array[2]);
        txtId.setVisible(array[3]);
        lblId.setVisible(array[3]);
        txtLogin.setVisible(array[4]);
        lblLogin.setVisible(array[4]);
        txtPassword.setVisible(array[5]);
        lblPassword.setVisible(array[5]);
    }
}
