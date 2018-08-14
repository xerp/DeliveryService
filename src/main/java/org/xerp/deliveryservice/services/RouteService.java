package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.dto.Path;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.dto.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    boolean saveRoute(String origin, String destination, List<Path> paths);

    boolean saveRoute(Point origin, Point destination, List<Path> paths);

    boolean updateRoute(String a, String b, List<Path> paths);

    boolean deleteRoute(String a, String b);

    Optional<Route> getRoute(String a, String b);

    Optional<Route> getRoute(Point a, Point b);

    List<Route> getRoutes();
}
