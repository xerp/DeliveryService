package org.xerp.deliveryservice.models;

import javax.persistence.*;

@Entity
public class PathDM {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_point_id", nullable = false, updatable = false)
    private PointDM origin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_point_id", nullable = false, updatable = false)
    private PointDM destination;

    private double time;
    private double cost;
    private double weight;

    public PathDM() {
    }

    public PathDM(PointDM origin, PointDM destination, double time, double cost) {
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.cost = cost;

        this.weight = time * cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PointDM getOrigin() {
        return origin;
    }

    public void setOrigin(PointDM origin) {
        this.origin = origin;
    }

    public PointDM getDestination() {
        return destination;
    }

    public void setDestination(PointDM destination) {
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
