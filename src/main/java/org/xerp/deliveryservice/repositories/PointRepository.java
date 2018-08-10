package org.xerp.deliveryservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xerp.deliveryservice.models.PointDM;

public interface PointRepository extends CrudRepository<PointDM, Long> {
}
