package org.xerp.deliveryservice.algorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.dto.Route;
import org.xerp.deliveryservice.ioc.ApplicationConfiguration;
import org.xerp.deliveryservice.services.PathService;

@RunWith(SpringRunner.class)
@Import(ApplicationConfiguration.class)
public class DijkstraTest {

    @Autowired
    @Qualifier("DIJKSTRA")
    private ShortestPathAlgorithm shortPathStrategy;

    @Autowired
    private PathService pathService;

    private Route route;

    @Before
    public void setUp() {
        var origin = "a";
        var destination = "b";

        var paths = new Paths(
                pathService.newPath("a", "c", 900, 20),
                pathService.newPath("c", "b", 900, 12),
                pathService.newPath("a", "e", 30, 5),
                pathService.newPath("a", "h", 10, 1),
                pathService.newPath("h", "e", 30, 1),
                pathService.newPath("e", "d", 3, 5),
                pathService.newPath("d", "f", 4, 50),
                pathService.newPath("f", "i", 45, 50),
                pathService.newPath("f", "g", 40, 50),
                pathService.newPath("i", "b", 65, 5),
                pathService.newPath("g", "b", 64, 73)
        );

        route = new Route();
        route.setOrigin(new Point(origin));
        route.setDestination(new Point(destination));
        route.setPaths(paths);
    }

    @Test
    public void findShortestPath() {
        var expectedValue = "A-E-D-F-G-B";

        var shortestPath = shortPathStrategy.findShortestPath(route.getOrigin(), route.getDestination(), route.getPaths());

        Assert.assertEquals(expectedValue, shortestPath);
    }
}