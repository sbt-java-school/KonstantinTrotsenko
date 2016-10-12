package home19.logicalOperations;

import home19.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Node for balance money of income
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class PartOfBalanceNode implements Node {
    private List<Node> childNodes = new ArrayList<>();

    /**
     * Result 1 - smt1/divider-smt2/divider..
     * @param values
     * @return result balance of income
     */
    @Override
    public double getResult(Map<String, Double> values) {
        List<Node> nodes = getChildNodes();
        double divider = nodes.get(0).getResult(values);
        double result = 1;
        if (nodes.size() > 1) {
            for (int i = 1; i < nodes.size(); i++) {
                result = result - nodes.get(i).getResult(values)/divider;
            }
        }
        return result;
    }

    @Override
    public void addNode(Node node) {
        childNodes.add(node);
    }

    @Override
    public void removeNode(Node node) {
        childNodes.remove(node);
    }

    @Override
    public List<Node> getChildNodes() {
        return childNodes;
    }
}
