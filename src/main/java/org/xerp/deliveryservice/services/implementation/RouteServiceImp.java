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
import java.util.Objects;
import java.util.Optional;

@Service
public class RouteServiceImp implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PointService pointService;

    private boolean pathContainOriginAndDestination(Path path, Point origin, Point destination) {
        return Objects.equals(path.getOrigin(), origin) && Objects.equals(path.getDestination(), destination);
    }

    private Point[] getPoints(String origin, String destination) {
        var pointOrigin = pointService.getPoint(origin);
        var pointDestination = pointService.getPoint(destination);

        if (!pointOrigin.isPresent() && !pointDestination.isPresent()) {
            throw new IllegalArgumentException("origin or destination points do not exist");
        }

        return new Point[]{pointOrigin.get(), pointDestination.get()};
    }

    @Override
    public Route saveRoute(String a, String b, List<Path> paths) {
        return saveRoute(new Point(a), new Point(b), paths);
    }

    @Override
    public Route saveRoute(Point origin, Point destination, List<Path> paths) {

        if (!pointService.exists(origin, destination)) {
            throw new IllegalArgumentException("A or B points do not exist");
        }

        if (paths.size() < 2) {
            throw new IllegalArgumentException("Path cannot be empty or has only one path");
        }

        if (paths.stream().anyMatch(p -> pathContainOriginAndDestination(p, origin, destination))) {
            throw new IllegalArgumentException("The route must not perform a direct delivery between the origin and the destination");
        }

        var route = new Route(new RouteId(origin, destination), paths);

        routeRepository.save(route);

        return route;
    }

    @Override
    public boolean updateRoute(String a, String b, List<Path> paths) {
        var route = getRoute(a, b);

        if (!route.isPresent()) {
            return false;
        }

        route.get().setPaths(paths);
        routeRepository.deleteById(route.get().getId());

        return routeRepository.save(route.get()) != null;
    }

    @Override
    public boolean removeRoute(String a, String b) {
        var points = getPoints(a, b);
        var routeID = new RouteId(points[0], points[1]);

        routeRepository.deleteById(routeID);
        return true;
    }

    @Override
    public Optional<Route> getRoute(String a, String b) {
        var points = getPoints(a, b);

        return getRoute(points[0], points[1]);
    }

    @Override
    public Optional<Route> getRoute(Point a, Point b) {
        var routeID = new RouteId(a, b);

        return routeRepository.findById(routeID);
    }
}
