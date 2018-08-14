package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.dto.Path;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.dto.Route;

import java.util.Map;
import java.util.Optional;

public interface PathService {
    Path newPath(Point origin, Point destination, double time, double cost);

    Path newPath(Map<String, Optional<Point>> pointsMap, String origin, String destination, double time, double cost);

    void setBestPaths(Route route);


}
