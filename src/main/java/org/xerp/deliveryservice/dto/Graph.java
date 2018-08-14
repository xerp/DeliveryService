package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph implements Serializable {
    private Node source;
    private Set<Node> nodes = new HashSet<>();

    public Graph() {
    }

    public Graph(Node source) {
        this.source = source;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public void addNodes(List<Node> nodes) {
        this.nodes.addAll(nodes);
    }

}
