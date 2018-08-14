package org.xerp.deliveryservice.services;

import org.xerp.deliveryservice.dto.Route;

public interface ShortPathStrategy {
    String findShortestPath(Route route);
}
