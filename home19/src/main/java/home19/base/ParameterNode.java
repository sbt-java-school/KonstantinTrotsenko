package home19.base;

import java.util.List;
import java.util.Map;

/**
 * Node realization to get parameter from map
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ParameterNode implements Node {

    private final String keyName;

    public ParameterNode(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public double getResult(Map<String, Double> values) {
        double value = values.get(keyName);
        return value;
    }

    @Override
    public void addNode(Node node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeNode(Node node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Node> getChildNodes() {
        return null;
    }
}
