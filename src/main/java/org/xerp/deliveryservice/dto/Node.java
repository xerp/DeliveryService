package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Serializable {

    private String name;
    private Integer distance;
    private Map<Node, Integer> adjacentNodes;
    private List<Node> shortestPath;

    public Node(String name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
        this.distance = Integer.MAX_VALUE;
        this.shortestPath = new LinkedList<>();
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void addDestination(Node destination, Integer distance) {
        getAdjacentNodes().put(destination, distance);
    }
}
