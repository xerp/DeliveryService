package org.xerp.deliveryservice.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class RouteDM {

    @EmbeddedId
    private RouteIdDM id;


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
    private List<PathDM> paths;

    public RouteDM() {
    }

    public RouteDM(RouteIdDM id, List<PathDM> paths) {
        this.id = id;
        this.paths = paths;
    }

    public RouteIdDM getId() {
        return id;
    }

    public void setId(RouteIdDM id) {
        this.id = id;
    }

    public List<PathDM> getPaths() {
        return paths;
    }

    public void setPaths(List<PathDM> paths) {
        this.paths = paths;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RouteDM)) {
            return false;
        }

        var otherRoute = (RouteDM) obj;

        return Objects.equals(this.id, otherRoute.id);
    }
}
