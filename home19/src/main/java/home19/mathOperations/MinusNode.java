package home19.mathOperations;

import home19.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Node for subtract
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class MinusNode implements Node {
    private List<Node> childNodes = new ArrayList<>();

    /**
     * Subtracts from the first element to all of the following
     * @param values
     * @return result of subtract
     */
    @Override
    public double getResult(Map<String, Double> values) {
        List<Node> nodes = getChildNodes();
        double result = nodes.get(0).getResult(values);
        if (nodes.size() > 1) {
            for (int i = 1; i < nodes.size(); i++) {
                result = result - nodes.get(i).getResult(values);
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
