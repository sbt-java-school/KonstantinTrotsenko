package home19.mathOperations;

import home19.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Node for multiplication
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class MultiplicationNode implements Node {
    private List<Node> childNodes = new ArrayList<>();

    /**
     * Multiplies all Nodes
     * @param values
     * @return result of multiplication
     */
    @Override
    public double getResult(Map<String, Double> values) {
        List<Node> nodes = getChildNodes();
        double result = 1;
        for (Node node : nodes) {
            result = result * node.getResult(values);
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
