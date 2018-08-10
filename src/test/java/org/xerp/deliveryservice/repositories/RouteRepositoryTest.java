package org.xerp.deliveryservice.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.models.Path;
import org.xerp.deliveryservice.models.Point;
import org.xerp.deliveryservice.models.Route;
import org.xerp.deliveryservice.models.RouteId;

import java.util.List;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PathRepository pathRepository;

    @Test
    public void addRoute() {
        var a = new Point("A");
        var b = new Point("B");
        var c = new Point("C");
        pointRepository.saveAll(List.of(a, b, c));

        var paths = List.of(new Path(a, c, 1.0, 5), new Path(c, b, 2.0, 6));
        pathRepository.saveAll(paths);

        var route = new Route(new RouteId(a, b), paths);
        routeRepository.save(route);

        var routeRetrieved = routeRepository.findById(new RouteId(a, b));

        assertEquals(paths.size(), routeRetrieved.get().getPaths().size());
    }

}