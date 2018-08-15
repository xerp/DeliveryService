package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.dto.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    boolean addSampleData();

    boolean saveRoute(String origin, String destination, Paths paths);

    boolean saveRoute(Point origin, Point destination, Paths paths);

    boolean updateRoute(String a, String b, Paths paths);

    boolean deleteRoute(String a, String b);

    Optional<Route> getRoute(String a, String b);

    Optional<Route> getRoute(Point a, Point b);

    List<Route> getRoutes();

}
