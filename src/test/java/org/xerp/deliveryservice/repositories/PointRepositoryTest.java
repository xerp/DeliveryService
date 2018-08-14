package org.xerp.deliveryservice.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xerp.deliveryservice.models.PointDM;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@DataJpaTest
@RunWith(SpringRunner.class)
public class PointRepositoryTest {

    @Autowired
    private PointRepository pointRepository;

    @Test
    public void getByName() {

        pointRepository.save(new PointDM("B"));
        var point = pointRepository.findByName("B");

        assertTrue(point.isPresent());
    }

    @Test
    public void addPointTest() {

        if (!pointRepository.findByName("A").isPresent()) {
            var newPoint = pointRepository.save(new PointDM("A"));

            assertNotNull(newPoint.getId());
        } else {
            assertTrue(true);
        }

    }
}