package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.List;

public class Graph implements Serializable {
    private Node source;
    private List<Node> nodes;

    public Graph() {
    }

    public Graph(Node source, List<Node> nodes) {
        this.source = source;
        this.nodes = nodes;
    }

    public Node getSource() {
        return source;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
