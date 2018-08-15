package org.xerp.deliveryservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Route;
import org.xerp.deliveryservice.services.RouteService;

import java.util.List;

@RestController
@RequestMapping("routes/")
public class RouteController {

    @Autowired
    private RouteService routeService;


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
        return routeService.addSampleData();
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
