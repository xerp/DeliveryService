package org.xerp.deliveryservice.services.implementation;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.dto.Path;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.dto.Route;
import org.xerp.deliveryservice.models.PathDM;
import org.xerp.deliveryservice.models.PointDM;
import org.xerp.deliveryservice.models.RouteDM;
import org.xerp.deliveryservice.models.RouteIdDM;
import org.xerp.deliveryservice.repositories.RouteRepository;
import org.xerp.deliveryservice.services.PathService;
import org.xerp.deliveryservice.services.PointService;
import org.xerp.deliveryservice.services.RouteService;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteServiceImp extends AbstractService implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PointService pointService;

    @Autowired
    private PathService pathService;

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
        List<Path> paths = modelMapper.map(routeDM.getPaths(), pathsType);

        route.setOrigin(modelMapper.map(routeDM.getId().getOrigin(), Point.class));
        route.setDestination(modelMapper.map(routeDM.getId().getDestination(), Point.class));
        route.setPaths(new Paths(paths));
        pathService.setShortestPath(route);

        return route;
    }

    @Override
    public boolean saveRoute(String a, String b, Paths paths) {
        return saveRoute(new Point(a), new Point(b), paths);
    }

    @Override
    public boolean saveRoute(Point origin, Point destination, Paths paths) {

        if (!pointService.exists(origin, destination)) {
            throw new IllegalArgumentException("origin or destination points do not exist");
        }

        if (paths.getPaths().size() < 2) {
            throw new IllegalArgumentException("PathDM cannot be empty or has only one path");
        }

        if (paths.getPaths().stream().anyMatch(p -> pathContainOriginAndDestination(p, origin, destination))) {
            throw new IllegalArgumentException("The route must not perform a direct delivery between the origin and the destination");
        }

        List<PathDM> pathsDM = modelMapper.map(paths.getPaths(), new TypeToken<List<PathDM>>() {
        }.getType());

        var routeID = new RouteIdDM(modelMapper.map(origin, PointDM.class), modelMapper.map(destination, PointDM.class));
        var route = new RouteDM(routeID, pathsDM);

        routeRepository.save(route);

        return route.getId().getOrigin().getId() != null && route.getId().getDestination().getId() != null;
    }

    @Override
    public boolean updateRoute(String a, String b, Paths paths) {
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

    @Override
    public boolean addSampleData() {
        var points = new String[]{"a", "b", "c", "h", "e", "d", "f", "i", "g"};

        if (!pointService.exists(points)) {
            pointService.savePoints(points);
        }

        var pointsMap = Arrays
                .stream(points)
                .collect(Collectors.toMap(p -> p, p -> pointService.getPoint(p)));


        var paths = new Paths(
                pathService.newPath(pointsMap, "a", "c", 900, 20),
                pathService.newPath(pointsMap, "c", "b", 900, 12),
                pathService.newPath(pointsMap, "a", "e", 30, 5),
                pathService.newPath(pointsMap, "a", "h", 10, 1),
                pathService.newPath(pointsMap, "h", "e", 30, 1),
                pathService.newPath(pointsMap, "e", "d", 3, 5),
                pathService.newPath(pointsMap, "d", "f", 4, 50),
                pathService.newPath(pointsMap, "f", "i", 45, 50),
                pathService.newPath(pointsMap, "f", "g", 40, 50),
                pathService.newPath(pointsMap, "i", "b", 65, 5),
                pathService.newPath(pointsMap, "g", "b", 64, 73)
        );

        return saveRoute(pointsMap.get("a").get(), pointsMap.get("b").get(), paths);
    }
}
