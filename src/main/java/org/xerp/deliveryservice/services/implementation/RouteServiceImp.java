package org.xerp.deliveryservice.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.models.Path;
import org.xerp.deliveryservice.models.Point;
import org.xerp.deliveryservice.models.Route;
import org.xerp.deliveryservice.models.RouteId;
import org.xerp.deliveryservice.repositories.RouteRepository;
import org.xerp.deliveryservice.services.PointService;
import org.xerp.deliveryservice.services.RouteService;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImp implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PointService pointService;

    @Override
    public Route saveRoute(String a, String b, List<Path> paths) {
        return saveRoute(new Point(a), new Point(b), paths);
    }

    @Override
    public Route saveRoute(Point origin, Point destination, List<Path> paths) {

        if (!pointService.exists(origin, destination)) {
            throw new IllegalArgumentException("A or B points do not exist");
        }

        var route = new Route(new RouteId(origin, destination), paths);

        routeRepository.save(route);

        return route;
    }

    @Override
    public boolean updateRoute(String a, String b, List<Path> paths) {
        return false;
    }

    @Override
    public boolean removeRoute(String a, String b) {
        return false;
    }

    @Override
    public Optional<Route> getRoute(String a, String b) {
        return Optional.empty();
    }
}
