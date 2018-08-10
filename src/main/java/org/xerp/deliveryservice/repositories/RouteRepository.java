package org.xerp.deliveryservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xerp.deliveryservice.models.Route;
import org.xerp.deliveryservice.models.RouteId;

public interface RouteRepository extends CrudRepository<Route, RouteId> {
}
