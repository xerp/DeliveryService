package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Route implements Serializable {

    private Point origin;
    private Point destination;
    private List<Path> allPaths;
    private List<Path> bestPaths;

    public Route() {
        this.bestPaths = new ArrayList<>();
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

    public List<Path> getAllPaths() {
        return allPaths;
    }

    public void setAllPaths(List<Path> allPaths) {
        this.allPaths = allPaths;
    }

    public List<Path> getBestPaths() {
        return bestPaths;
    }

    public void setBestPaths(List<Path> bestPath) {
        this.bestPaths = bestPath;
    }
}
