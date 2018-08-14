package org.xerp.deliveryservice.models;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RouteIdDM implements Serializable {

    @ManyToOne
    @JoinColumn(name = "origin_point_id", nullable = false, updatable = false)
    private PointDM origin;
    @ManyToOne
    @JoinColumn(name = "destination_point_id", nullable = false, updatable = false)
    private PointDM destination;

    public RouteIdDM() {
    }

    public RouteIdDM(PointDM origin, PointDM destination) {
        this.destination = destination;
        this.origin = origin;
    }

    public PointDM getOrigin() {
        return origin;
    }

    public PointDM getDestination() {
        return destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RouteIdDM)) {
            return false;
        }

        var otherId = (RouteIdDM) obj;

        return Objects.equals(destination, otherId.destination) && Objects.equals(origin, otherId.origin);
    }
}
