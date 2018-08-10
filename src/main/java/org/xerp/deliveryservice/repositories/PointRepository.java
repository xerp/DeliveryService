package org.xerp.deliveryservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xerp.deliveryservice.models.Point;

import java.util.Optional;

public interface PointRepository extends CrudRepository<Point, Long> {

    Optional<Point> findByName(String name);
}
