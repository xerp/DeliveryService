package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.dto.Point;

import java.util.List;
import java.util.Optional;

public interface PointService {
    boolean savePoints(String... points);

    boolean savePoint(String name);

    boolean pointExists(String name);

    boolean pointExists(Point point);

    boolean exists(Point... points);

    boolean exists(String... names);

    Optional<Point> getPoint(String name);

    List<Point> getPoints();

    boolean deletePoint(String name);
}
