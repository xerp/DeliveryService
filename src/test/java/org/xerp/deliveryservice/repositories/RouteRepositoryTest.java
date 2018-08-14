package org.xerp.deliveryservice.repositories;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.models.PathDM;
import org.xerp.deliveryservice.models.PointDM;
import org.xerp.deliveryservice.models.RouteDM;
import org.xerp.deliveryservice.models.RouteIdDM;

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

    @Ignore
    @Test
    public void addRoute() {
        var d = new PointDM("D");
        var e = new PointDM("E");
        var f = new PointDM("F");
        pointRepository.saveAll(List.of(d, e, f));

        var paths = List.of(
                new PathDM(d, f, 1.0, 5),
                new PathDM(f, e, 2.0, 6)
        );
        pathRepository.saveAll(paths);

        var route = new RouteDM(new RouteIdDM(d, e), paths);
        routeRepository.save(route);

        var routeRetrieved = routeRepository.findById(new RouteIdDM(d, e));

        assertEquals(paths.size(), routeRetrieved.get().getPaths().size());
    }

}