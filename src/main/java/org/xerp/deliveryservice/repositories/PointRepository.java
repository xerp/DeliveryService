package org.xerp.deliveryservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xerp.deliveryservice.models.PointDM;

import java.util.Optional;

public interface PointRepository extends CrudRepository<PointDM, Long> {

    Optional<PointDM> findByName(String name);

    boolean existsByName(String name);
}
