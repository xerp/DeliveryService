package org.xerp.deliveryservice.algorithms;

import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Point;

public interface ShortestPathAlgorithm {
    String findShortestPath(Point source, Point destination, Paths paths);
}
