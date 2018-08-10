package org.xerp.deliveryservice.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.ioc.ApplicationConfiguration;
import org.xerp.deliveryservice.models.Path;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@Import(ApplicationConfiguration.class)
public class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Autowired
    private PointService pointService;


    @Test
    public void saveRouteTest() {
        var pointA = pointService.getPoint("A");
        var pointB = pointService.getPoint("B");
        var pointC = pointService.getPoint("C");
        var paths = Arrays.asList(
                new Path(pointA.get(), pointB.get(), 1.0, 2.0),
                new Path(pointB.get(), pointC.get(), 2.0, 3.0)
        );

        var route = routeService.saveRoute(pointA.get(), pointC.get(), paths);

        Assert.assertNotNull(route);
    }
}