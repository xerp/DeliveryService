package org.xerp.deliveryservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.xerp.deliveryservice.dto.Path;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Route;
import org.xerp.deliveryservice.services.PointService;
import org.xerp.deliveryservice.services.RouteService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("routes/")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PointService pointService;

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
        if (!pointService.exists("a", "b", "c")) {
            pointService.savePoints("a", "b", "c");
        }

        var pointA = pointService.getPoint("a");
        var pointB = pointService.getPoint("b");
        var pointC = pointService.getPoint("c");


        var paths = Arrays.asList(
                new Path(pointA.get(), pointB.get(), 1.0, 2.0),
                new Path(pointB.get(), pointC.get(), 2.0, 3.0)
        );

        return routeService.saveRoute(pointA.get(), pointC.get(), paths);
    }

    @PostMapping("{origin}/to/{destination}")
    public boolean addRoute(@PathVariable String origin, @PathVariable String destination,
                            @RequestBody Paths paths) {
        return routeService.saveRoute(origin, destination, paths.getPaths());
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
        return routeService.updateRoute(origin, destination, paths.getPaths());
    }
}
