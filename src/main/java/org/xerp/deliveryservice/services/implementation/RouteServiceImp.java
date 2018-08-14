package org.xerp.deliveryservice.services.implementation;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.dto.Path;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.dto.Route;
import org.xerp.deliveryservice.models.PathDM;
import org.xerp.deliveryservice.models.PointDM;
import org.xerp.deliveryservice.models.RouteDM;
import org.xerp.deliveryservice.models.RouteIdDM;
import org.xerp.deliveryservice.repositories.RouteRepository;
import org.xerp.deliveryservice.services.PointService;
import org.xerp.deliveryservice.services.RouteService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RouteServiceImp extends AbstractService implements RouteService {

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

    private Route mapRoute(RouteDM routeDM, Type pathsType) {
        var route = new Route();
        route.setOrigin(modelMapper.map(routeDM.getId().getOrigin(), Point.class));
        route.setDestination(modelMapper.map(routeDM.getId().getDestination(), Point.class));
        route.setAllPaths(modelMapper.map(routeDM.getPaths(), pathsType));

        return route;
    }

    @Override
    public boolean saveRoute(String a, String b, List<Path> paths) {
        return saveRoute(new Point(a), new Point(b), paths);
    }

    @Override
    public boolean saveRoute(Point origin, Point destination, List<Path> paths) {

        if (!pointService.exists(origin, destination)) {
            throw new IllegalArgumentException("A or B points do not exist");
        }

        if (paths.size() < 2) {
            throw new IllegalArgumentException("PathDM cannot be empty or has only one path");
        }

        if (paths.stream().anyMatch(p -> pathContainOriginAndDestination(p, origin, destination))) {
            throw new IllegalArgumentException("The route must not perform a direct delivery between the origin and the destination");
        }

        List<PathDM> pathsDM = modelMapper.map(paths, new TypeToken<List<PathDM>>() {
        }.getType());

        var routeID = new RouteIdDM(modelMapper.map(origin, PointDM.class), modelMapper.map(destination, PointDM.class));
        var route = new RouteDM(routeID, pathsDM);

        routeRepository.save(route);

        return route.getId().getOrigin().getId() != null && route.getId().getDestination().getId() != null;
    }

    @Override
    public boolean updateRoute(String a, String b, List<Path> paths) {
        var deleted = deleteRoute(a, b);

        if (!deleted) {
            return false;
        }

        var pointOrigin = pointService.getPoint(a);
        var pointDestination = pointService.getPoint(b);
        return saveRoute(pointOrigin.get(), pointDestination.get(), paths);
    }

    @Override
    public boolean deleteRoute(String a, String b) {
        var points = getPoints(a, b);
        var routeID = new RouteIdDM(
                modelMapper.map(points[0], PointDM.class),
                modelMapper.map(points[1], PointDM.class)
        );

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
        var routeID = new RouteIdDM(
                modelMapper.map(a, PointDM.class),
                modelMapper.map(b, PointDM.class)
        );
        var routeDM = routeRepository.findById(routeID);

        if (!routeDM.isPresent()) {
            return Optional.empty();
        }
        var pathsType = new TypeToken<List<Path>>() {
        }.getType();


        return Optional.of(mapRoute(routeDM.get(), pathsType));
    }

    @Override
    public List<Route> getRoutes() {
        var routes = new ArrayList<Route>();
        var pathsType = new TypeToken<List<Path>>() {
        }.getType();

        routeRepository.findAll().forEach(rdm -> routes.add(mapRoute(rdm, pathsType)));

        return routes;
    }
}
