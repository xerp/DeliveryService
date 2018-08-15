package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Node implements Serializable {

    private String name;
    private Integer distance;
    private List<Edge> edges;
    private List<Node> shortestPath;

    public Node(String name) {
        this.name = name;
        this.edges = new LinkedList<>();
        this.shortestPath = new LinkedList<>();
        this.distance = Integer.MAX_VALUE;
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

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void addDestination(Node destination, Integer cost) {
        getEdges().add(new Edge(this, destination, cost));
    }
}
