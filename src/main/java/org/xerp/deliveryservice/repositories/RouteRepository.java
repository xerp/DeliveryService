package org.xerp.deliveryservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xerp.deliveryservice.models.RouteDM;
import org.xerp.deliveryservice.models.RouteIdDM;

public interface RouteRepository extends CrudRepository<RouteDM, RouteIdDM> {
}
