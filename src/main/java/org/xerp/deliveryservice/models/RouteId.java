package org.xerp.deliveryservice.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RouteId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "origin_point_id", nullable = false, updatable = false)
    private Point origin;
    @ManyToOne
    @JoinColumn(name = "destination_point_id", nullable = false, updatable = false)
    private Point destination;

    public RouteId() {
    }

    public RouteId(Point origin, Point destination) {
        this.destination = destination;
        this.origin = origin;
    }

    public Point getOrigin() {
        return origin;
    }

    public Point getDestination() {
        return destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RouteId)) {
            return false;
        }

        var otherId = (RouteId) obj;

        return Objects.equals(destination, otherId.destination) && Objects.equals(origin, otherId.origin);
    }
}
