package org.xerp.deliveryservice.dto;

import java.io.Serializable;

public class Route implements Serializable {

    private Point origin;
    private Point destination;
    private Paths allPaths;
    private Paths bestPaths;

    public Route() {
        this.bestPaths = new Paths();
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

    public Paths getAllPaths() {
        return allPaths;
    }

    public void setAllPaths(Paths allPaths) {
        this.allPaths = allPaths;
    }

    public Paths getBestPaths() {
        return bestPaths;
    }

    public void setBestPaths(Paths bestPath) {
        this.bestPaths = bestPath;
    }
}
