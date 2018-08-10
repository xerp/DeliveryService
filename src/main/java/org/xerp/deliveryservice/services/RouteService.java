package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.models.Path;
import org.xerp.deliveryservice.models.Point;
import org.xerp.deliveryservice.models.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Route saveRoute(String origin, String destination, List<Path> paths);

    Route saveRoute(Point origin, Point destination, List<Path> paths);

    boolean updateRoute(String a, String b, List<Path> paths);

    boolean removeRoute(String a, String b);

    Optional<Route> getRoute(String a, String b);

    Optional<Route> getRoute(Point a, Point b);
}
