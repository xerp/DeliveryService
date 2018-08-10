package org.xerp.deliveryservice.dto;

import java.util.List;

public class Route {

    private Point origin;
    private Point destination;

    private List<Path> paths;

    public Route(Point origin, Point destination, List<Path> paths) {
        this.origin = origin;
        this.destination = destination;
        this.paths = paths;
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

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}
