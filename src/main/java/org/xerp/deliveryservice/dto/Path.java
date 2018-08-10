package org.xerp.deliveryservice.dto;

public class Path {

    private Point origin;
    private Point destination;
    private double time;
    private double cost;

    public Path(Point origin, Point destination, double time, double cost) {
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.cost = cost;
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

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
