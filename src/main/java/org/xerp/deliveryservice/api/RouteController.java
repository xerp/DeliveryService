package org.xerp.deliveryservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.xerp.deliveryservice.models.Path;
import org.xerp.deliveryservice.models.Route;
import org.xerp.deliveryservice.services.PointService;
import org.xerp.deliveryservice.services.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("routes/")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PointService pointService;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalArgumentException.class)
    public String exceptionHandler(HttpServletRequest req, IllegalArgumentException ex) {
        return String.format("{\"error\": \"%s\"}", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public void notFoundHandler() {
    }

    @GetMapping
    public String greeting() {
        return "Welcome to route RESTful web service";
    }

    @PostMapping
    public boolean sampleData() {
        if (!pointService.exists("A", "B", "C")) {
            pointService.savePoints("A", "B", "C");
        }

        var pointA = pointService.getPoint("A");
        var pointB = pointService.getPoint("B");
        var pointC = pointService.getPoint("C");

        var paths = Arrays.asList(
                new Path(pointA.get(), pointB.get(), 1.0, 2.0),
                new Path(pointB.get(), pointC.get(), 2.0, 3.0)
        );
        var route = routeService.saveRoute(pointA.get(), pointC.get(), paths);

        return route != null;
    }

    @GetMapping("route")
    public Route getRoute(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        var route = routeService.getRoute(origin, destination);

        if (route.isPresent()) {
            return route.get();
        } else {
            throw new NullPointerException();
        }
    }

    @DeleteMapping("route")
    public boolean deleteRoute(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        return routeService.removeRoute(origin, destination);
    }

    @PutMapping("route")
    public boolean updateRoute(@RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestBody List<Path> paths) {
        return routeService.updateRoute(origin, destination, paths);
    }
}
