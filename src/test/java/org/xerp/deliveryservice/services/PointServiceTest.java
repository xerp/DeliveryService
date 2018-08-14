package org.xerp.deliveryservice.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.ioc.ApplicationConfiguration;

@RunWith(SpringRunner.class)
@Import(ApplicationConfiguration.class)
public class PointServiceTest {

    @Autowired
    private PointService pointService;

    @Test
    public void savePoints() {

        if (!pointService.exists("A", "B", "C")) {
            var pointsSaved = pointService.savePoints("A", "B", "C");

            Assert.assertTrue(pointsSaved);
        } else {
            Assert.assertTrue(true);
        }

    }
}