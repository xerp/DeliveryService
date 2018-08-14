package org.xerp.deliveryservice.services.implementation;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.models.PointDM;
import org.xerp.deliveryservice.repositories.PointRepository;
import org.xerp.deliveryservice.services.PointService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PointServiceImp extends AbstractService implements PointService {

    @Autowired
    private PointRepository pointRepository;

    @Override
    public boolean savePoints(String... points) {

        var pointList = Arrays
                .stream(points)
                .map(PointDM::new)
                .collect(Collectors.toList());

        pointRepository.saveAll(pointList);
        return pointList
                .stream()
                .allMatch(p -> p.getId() != null);
    }

    @Override
    public boolean savePoint(String name) {
        return pointRepository.save(new PointDM(name)).getId() != null;
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
        var pointDM = pointRepository.findByName(name.toUpperCase());

        if (!pointDM.isPresent()) {
            Optional.empty();
        }

        return Optional.of(modelMapper.map(pointDM.get(), Point.class));
    }

    @Override
    public List<Point> getPoints() {
        var optionalType = new TypeToken<List<Point>>() {
        }.getType();

        return modelMapper.map(pointRepository.findAll(), optionalType);
    }

    @Override
    public boolean deletePoint(String name) {
        var point = pointRepository.findByName(name.toUpperCase());

        if (!point.isPresent()) {
            return false;
        }

        pointRepository.deleteById(point.get().getId());
        return true;
    }
}
