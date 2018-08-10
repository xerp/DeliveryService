package org.xerp.deliveryservice.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.models.Point;
import org.xerp.deliveryservice.repositories.PointRepository;
import org.xerp.deliveryservice.services.PointService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PointServiceImp implements PointService {

    @Autowired
    private PointRepository pointRepository;

    @Override
    public List<Point> savePoints(String... points) {

        var pointList = Arrays
                .stream(points)
                .map(Point::new)
                .collect(Collectors.toList());

        pointRepository.saveAll(pointList);

        return pointList;
    }

    @Override
    public boolean pointExists(String name) {
        return pointRepository.existsByName(name.toUpperCase());
    }

    @Override
    public boolean pointExists(Point point) {
        return pointRepository.existsByName(point.getName());
    }

    @Override
    public boolean exists(Point... points) {
        return Arrays.stream(points)
                .allMatch(this::pointExists);
    }

    @Override
    public boolean exists(String... names) {
        return Arrays.stream(names)
                .allMatch(this::pointExists);
    }

    @Override
    public Optional<Point> getPoint(String name) {
        return pointRepository.findByName(name.toUpperCase());
    }
}
