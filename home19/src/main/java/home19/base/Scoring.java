package home19.base;

import home19.logicalOperations.PartOfBalanceNode;
import home19.mathOperations.DivisionNode;
import home19.mathOperations.MinusNode;
import home19.mathOperations.MultiplicationNode;
import home19.mathOperations.PlusNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Class simple scoring system
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Scoring {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scoring.class);

    /**
     * Do scoring for bank client
     * @param map with parameter for work
     * @return double[] with result parameters
     */
    public double[] startScoring(Map<String, Double> map) {
        Node parameterSalary = new ParameterNode("Salary");
        Node parameterCity = new ParameterNode("City");
        Node parameterMonth = new ParameterNode("PeriodMonth");
        Node parameterAmountOfCredit = new ParameterNode("AmountOfCredit");
        Node parameterAge = new ParameterNode("Age");
        Node parameterMaritalStatus = new ParameterNode("MaritalStatus");
        Node parameterOwnership = new ParameterNode("Ownership");
        Node parameterCar = new ParameterNode("Car");
        Node parameterChildren = new ParameterNode("Children");
        Node parameterDependents = new ParameterNode("Dependents");

        Node minusNode = new MinusNode();
        Node divisionNode = new DivisionNode();
        Node partOfBalanceNode = new PartOfBalanceNode();
        Node plusNode = new PlusNode();
        Node multiplicationNode = new MultiplicationNode();

        //income
        minusNode.addNode(parameterSalary);
        minusNode.addNode(parameterCity);
        double income = minusNode.getResult(map);

        //payment
        divisionNode.addNode(parameterAmountOfCredit);
        divisionNode.addNode(parameterMonth);
        double payment = divisionNode.getResult(map);

        //get credit factor
        partOfBalanceNode.addNode(minusNode);
        partOfBalanceNode.addNode(divisionNode);
        double factorCredit = partOfBalanceNode.getResult(map);

        //Addition all client factors
        plusNode.addNode(parameterAge);
        plusNode.addNode(parameterMaritalStatus);
        plusNode.addNode(parameterOwnership);
        plusNode.addNode(parameterCar);
        plusNode.addNode(parameterChildren);
        plusNode.addNode(parameterDependents);
        double factorClient = plusNode.getResult(map);

        //Multiplication client factor on credit factor
        multiplicationNode.addNode(partOfBalanceNode);
        multiplicationNode.addNode(plusNode);
        double factorTotal = multiplicationNode.getResult(map);

        LOGGER.info("Income: " + income);
        LOGGER.info("Payment: " + payment);
        LOGGER.info("Factor Credit: " + factorCredit);
        LOGGER.info("Factor Client: " + factorClient);
        LOGGER.info("Factor Total: " + factorTotal);

        return new double[]{income, payment, factorCredit, factorClient, factorTotal};
    }
}
