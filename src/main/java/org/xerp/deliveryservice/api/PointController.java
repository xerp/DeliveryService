package org.xerp.deliveryservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.services.PointService;

import java.util.List;

@RestController
@RequestMapping("points/")
public class PointController {

    @Autowired
    private PointService pointService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NullPointerException.class)
    public void notFoundHandler() {
    }

    @GetMapping("index")
    public String greeting() {
        return "Welcome to point RESTful web service";
    }

    @GetMapping
    public List<Point> getPoints() {
        return pointService.getPoints();
    }

    @GetMapping("{name}")
    public Point getPoint(@PathVariable String name) {
        var point = pointService.getPoint(name);

        if (!point.isPresent()) {
            throw new NullPointerException();
        }

        return point.get();
    }

    @PostMapping("{name}")
    public boolean addPoint(@PathVariable String name) {
        if (pointService.exists(name)) {
            return false;
        }

        return pointService.savePoint(name);
    }

    @DeleteMapping("{name}")
    public boolean deletePoint(@PathVariable String name) {
        return pointService.deletePoint(name);
    }

}
