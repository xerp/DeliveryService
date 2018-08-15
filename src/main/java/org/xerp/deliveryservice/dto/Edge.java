package org.xerp.deliveryservice.dto;

import java.io.Serializable;

public class Edge implements Serializable {
    private Node fromNode;
    private Node toNode;
    private Integer cost;

    public Edge(Node fromNode, Node toNode, Integer cost) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.cost = cost;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
