package org.xerp.deliveryservice.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Route {

    @EmbeddedId
    private RouteId id;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "route_path",
            joinColumns = {
                    @JoinColumn(name = "route_origin_point_id"),
                    @JoinColumn(name = "route_destination_point_id")
            },
            inverseJoinColumns = @JoinColumn(name = "path_id")
    )
    private List<Path> paths;

    public Route() {
    }

    public Route(RouteId id, List paths) {
        this.id = id;
        this.paths = paths;
    }

    public RouteId getId() {
        return id;
    }

    public void setId(RouteId id) {
        this.id = id;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Route)) {
            return false;
        }

        var otherRoute = (Route) obj;

        return Objects.equals(this.id, otherRoute.id);
    }
}
