package org.xerp.deliveryservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Route;
import org.xerp.deliveryservice.services.PathService;
import org.xerp.deliveryservice.services.PointService;
import org.xerp.deliveryservice.services.RouteService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("routes/")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PointService pointService;

    @Autowired
    private PathService pathService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public void notFoundHandler() {
    }

    @GetMapping("index")
    public String greeting() {
        return "Welcome to route RESTful web service";
    }

    @GetMapping
    public List<Route> getRoutes() {
        return routeService.getRoutes();
    }

    @PostMapping
    public boolean sampleData() {
        var points = new String[]{"a", "b", "c", "h", "e", "d", "f", "i", "g"};

        if (!pointService.exists(points)) {
            pointService.savePoints(points);
        }

        var pointsMap = Arrays
                .stream(points)
                .collect(Collectors.toMap(p -> p, p -> pointService.getPoint(p)));


        var paths = new Paths(
                pathService.newPath(pointsMap, "a", "c", 1, 20),
                pathService.newPath(pointsMap, "c", "b", 1, 12),
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

        return routeService.saveRoute(pointsMap.get("a").get(), pointsMap.get("b").get(), paths);
    }

    @PostMapping("{origin}/to/{destination}")
    public boolean addRoute(@PathVariable String origin, @PathVariable String destination,
                            @RequestBody Paths paths) {
        return routeService.saveRoute(origin, destination, paths);
    }

    @GetMapping("{origin}/to/{destination}")
    public Route getRoute(@PathVariable String origin, @PathVariable String destination) {
        var route = routeService.getRoute(origin, destination);

        if (!route.isPresent()) {
            throw new NullPointerException();
        }

        return route.get();
    }

    @DeleteMapping("{origin}/to/{destination}")
    public boolean deleteRoute(@PathVariable String origin, @PathVariable String destination) {
        return routeService.deleteRoute(origin, destination);
    }

    @PutMapping("{origin}/to/{destination}")
    public boolean updateRoute(@PathVariable String origin, @PathVariable String destination,
                               @RequestBody Paths paths) {
        return routeService.updateRoute(origin, destination, paths);
    }
}
