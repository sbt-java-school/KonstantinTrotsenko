package home19.mathOperations;

import home19.base.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Node for addition
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class PlusNode implements Node {
    private List<Node> childNodes = new ArrayList<>();

    /**
     * Adds all nodes
     * @param values
     * @return result of addition
     */
    @Override
    public double getResult(Map<String, Double> values) {
        List<Node> nodes = getChildNodes();
        double result = 0;
        for (Node node : nodes) {
            result = result + node.getResult(values);
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
