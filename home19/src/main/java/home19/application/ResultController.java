package home19.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Result window
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ResultController implements Initializable {
    @FXML
    private Label lblNameSet;
    @FXML
    private Label lblIncomeSet;
    @FXML
    private Label lblPaymentSet;
    @FXML
    private Label lblFactorCreditSet;
    @FXML
    private Label lblFactorClientSet;
    @FXML
    private Label lblFactorTotalSet;
    @FXML
    private Label lblMarkSet;

    /**
     * Set results data to labels
     * @param name client
     * @param result scoring data
     */
    public void setResult(String name, double[] result) {
        lblNameSet.setText(name);
        lblIncomeSet.setText(String.valueOf(result[0]));
        lblPaymentSet.setText(String.valueOf(result[1]));
        lblFactorCreditSet.setText(String.valueOf(result[2]));
        lblFactorClientSet.setText(String.valueOf(result[3]));
        lblFactorTotalSet.setText(String.valueOf(result[4]));
        if (result[4] > 0.14) {
            lblMarkSet.setText("Кредит одобрен");
        } else {
            lblMarkSet.setText("Кредит не одобрен");
        }
        if (result[2] > 1.1) {
            lblMarkSet.setText("Доход меньше платежа");
        }
        if (result[2] < 0) {
            lblMarkSet.setText("Высокий платеж");
        }
        if (result[2] < 0) {
            lblMarkSet.setText("Высокий платеж");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
