package org.xerp.deliveryservice.dto;

import java.io.Serializable;

public class Route implements Serializable {

    private Point origin;
    private Point destination;
    private Paths paths;
    private String shortestPath;

    public Route() {
        this.shortestPath = "";
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public Paths getPaths() {
        return paths;
    }

    public void setPaths(Paths paths) {
        this.paths = paths;
    }

    public String getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(String bestPath) {
        this.shortestPath = bestPath;
    }
}
