package org.xerp.deliveryservice.services.implementation;

import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.dto.Path;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.services.PathService;

import java.util.Map;
import java.util.Optional;

@Service
public class PathServiceImp extends AbstractService implements PathService {
    @Override
    public Path newPath(Point origin, Point destination, double time, double cost) {
        return new Path(origin, destination, time, cost);
    }

    @Override
    public Path newPath(Map<String, Optional<Point>> pointsMap, String origin, String destination, double time, double cost) {
        if (!pointsMap.get(origin).isPresent() && pointsMap.get(destination).isPresent()) {
            return null;
        }

        var pointOrigin = pointsMap.get(origin).get();
        var pointDestination = pointsMap.get(destination).get();

        return new Path(pointOrigin, pointDestination, time, cost);
    }
}
