package org.xerp.deliveryservice.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.models.Point;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Test
    public void getByName() {

        pointRepository.save(new Point("B"));
        var point = pointRepository.findByName("B");

        assertTrue(point.isPresent());
    }

    @Test
    public void addPointTest() {
        var newPoint = pointRepository.save(new Point("A"));

        assertNotNull(newPoint.getId());
    }
}