package org.xerp.deliveryservice.models;

import org.xerp.deliveryservice.dto.Point;

import javax.persistence.*;

@Entity
@Table(name = "Path")
public class PathDM {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_point_id", nullable = false, updatable = false)
    private Point origin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_point_id", nullable = false, updatable = false)
    private Point destination;

    private double time;
    private double cost;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
