package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Node implements Serializable {

    private String name;
    private Double distance;
    private Map<Node, Double> adjacentNodes;

    public Node(String name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
        this.distance = Double.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Map<Node, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public void addDestination(Node destination, Double distance) {
        getAdjacentNodes().put(destination, distance);
    }
}
