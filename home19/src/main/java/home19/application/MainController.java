package home19.application;

import home19.base.Scoring;
import home19.application.textFields.NumberTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple questionnaire for scoring
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class MainController extends Application {
    @FXML
    private TextField txtName;
    @FXML
    private NumberTextField txtSalary;
    @FXML
    private NumberTextField txtAmountOfCredit;
    @FXML
    private NumberTextField txtPeriodMonth;
    @FXML
    private Label lblName;
    @FXML
    private Label lblSalary;
    @FXML
    private Label lblAmountOfCredit;
    @FXML
    private Label lblPeriodMonth;
    @FXML
    private ComboBox cbAge;
    @FXML
    private ComboBox cbMaritalStatus;
    @FXML
    private ComboBox cbCity;
    @FXML
    private ComboBox cbChildren;
    @FXML
    private ComboBox cbOwnership;
    @FXML
    private ComboBox cbDependents;
    @FXML
    private ComboBox cbCar;
    @FXML
    private Button btnStart;

    private String name;
    private double salary;
    private double amountOfCredit;
    private double periodMonth;
    private double age;
    private double maritalStatus;
    private double city;
    private double children;
    private double dependents;
    private double ownership;
    private double car;

    private Map<String, Double> client;
    private final Scoring scoring = new Scoring();
    private double[] results;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("Scoring system");
        primaryStage.setScene(new Scene(root, 360, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Questionnaire with parameters for scoring
     * @param event
     * @throws IOException
     */
    public void startMainController(ActionEvent event) throws IOException {
        if (isEmptyFields()) {
            client = new HashMap<>();
            name = txtName.getText().trim();

            salary = Double.parseDouble(txtSalary.getText().trim());
            client.put("Salary", salary);

            amountOfCredit = Double.parseDouble(txtAmountOfCredit.getText().trim());
            client.put("AmountOfCredit", amountOfCredit);

            periodMonth = Double.parseDouble(txtPeriodMonth.getText().trim());
            client.put("PeriodMonth", periodMonth);

            String ageCb = (String) cbAge.getValue();
            switch (ageCb) {
                case "25-35":
                    age = 0.20;
                    break;
                case "35-50":
                    age = 0.25;
                    break;
                case "50 и более":
                    age = 0.20;
                    break;
                //до 25
                default:
                    age = 0.15;
                    break;
            }
            client.put("Age", age);

            String cityCb = (String) cbCity.getValue();
            switch (cityCb) {
                case "Москва)":
                    city = 15000;
                    break;
                //Новосибирск
                default:
                    city = 8000;
                    break;
            }
            client.put("City", city);

            String maritalStatusCb = (String) cbMaritalStatus.getValue();
            switch (maritalStatusCb) {
                case "Холост (не замужем)":
                    maritalStatus = 0.10;
                    break;
                //Женат (замужем)
                default:
                    maritalStatus = 0.15;
                    break;
            }
            client.put("MaritalStatus", maritalStatus);

            String childrenCb = (String) cbChildren.getValue();
            switch (childrenCb) {
                case "1":
                    children = 0.10;
                    break;
                case "2 и более":
                    children = 0.05;
                    break;
                //Нет детей
                default:
                    children = 0.15;
                    break;
            }
            client.put("Children", children);

            String dependentsCb = (String) cbDependents.getValue();
            switch (dependentsCb) {
                case "1":
                    dependents = -0.05;
                    break;
                case "2 и более":
                    dependents = -0.10;
                    break;
                //Нет иждивенцев
                default:
                    dependents = 0.00;
                    break;
            }
            client.put("Dependents", dependents);

            String ownershipCb = (String) cbOwnership.getValue();
            switch (ownershipCb) {
                case "Есть":
                    ownership = 0.05;
                    break;
                //Нет
                default:
                    ownership = 0.00;
                    break;
            }
            client.put("Ownership", ownership);

            String carCb = (String) cbCar.getValue();
            switch (carCb) {
                case "Есть":
                    car = 0.05;
                    break;
                //Нет
                default:
                    car = 0.00;
                    break;
            }
            client.put("Car", car);

            results = scoring.startScoring(client);

            startResultWindow(name, results);
        }
    }

    /**
     * Start result window
     * @param name client
     * @param results scoring
     * @throws IOException
     */
    private void startResultWindow(String name, double[] results) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/Result.fxml").openStream());
        ResultController resultController = loader.getController();
        resultController.setResult(name, results);
        primaryStage.setTitle("Result");
        primaryStage.setScene(new Scene(root, 300, 380));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Check empty fields
     * @return true if ok else false
     */
    private boolean isEmptyFields() {
        if (txtName.getText().trim().isEmpty()) {
            lblName.setTextFill(Color.RED);
            return false;
        } else {
            lblName.setTextFill(Color.BLACK);
        }
        if (txtSalary.getText().trim().isEmpty()) {
            lblSalary.setTextFill(Color.RED);
            return false;
        } else {
            lblSalary.setTextFill(Color.BLACK);
        }
        if (txtAmountOfCredit.getText().trim().isEmpty()) {
            lblAmountOfCredit.setTextFill(Color.RED);
            return false;
        } else {
            lblAmountOfCredit.setTextFill(Color.BLACK);
        }
        if (txtPeriodMonth.getText().trim().isEmpty()) {
            lblPeriodMonth.setTextFill(Color.RED);
            return false;
        } else {
            lblPeriodMonth.setTextFill(Color.BLACK);
        }
        return true;
    }
}
