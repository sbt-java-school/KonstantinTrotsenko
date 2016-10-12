package home19.base;

import java.util.List;
import java.util.Map;

/**
 * Interface Node for comparator
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public interface Node {
    double getResult(Map<String, Double> values);

    void addNode(Node node);

    void removeNode(Node node);

    List<Node> getChildNodes();
}
