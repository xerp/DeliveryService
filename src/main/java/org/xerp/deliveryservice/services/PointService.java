package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.models.Point;

import java.util.List;
import java.util.Optional;

public interface PointService {
    List<Point> savePoints(String... points);

    Point savePoint(String name);

    boolean pointExists(String name);

    boolean pointExists(Point point);

    boolean exists(Point... points);

    boolean exists(String... names);

    Optional<Point> getPoint(String name);

    List<Point> getPoints();

    boolean deletePoint(String name);
}
