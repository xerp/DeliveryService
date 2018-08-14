package org.xerp.deliveryservice.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.ioc.ApplicationConfiguration;
import org.xerp.deliveryservice.models.PathDM;
import org.xerp.deliveryservice.models.PointDM;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@Import(ApplicationConfiguration.class)
public class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PointService pointService;

    private Optional<PointDM> pointA;
    private Optional<PointDM> pointB;
    private Optional<PointDM> pointC;

    @Before
    public void setUp() {
        if (!pointService.exists("A", "B", "C")) {
            pointService.savePoints("A", "B", "C");
        }

        pointA = pointService.getPoint("A");
        pointB = pointService.getPoint("B");
        pointC = pointService.getPoint("C");
    }


    @Test
    public void saveRouteTest() {
        var paths = Arrays.asList(
                new PathDM(pointA.get(), pointB.get(), 1.0, 2.0),
                new PathDM(pointB.get(), pointC.get(), 2.0, 3.0)
        );
        var route = routeService.saveRoute(pointA.get(), pointC.get(), paths);

        Assert.assertNotNull(route);
    }

    @Test
    public void getRouteTest() {
        var route = routeService.getRoute(pointA.get(), pointC.get());

        Assert.assertTrue(route.isPresent() && !route.get().getAllPaths().isEmpty());
    }

}