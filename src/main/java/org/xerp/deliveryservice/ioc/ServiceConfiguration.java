package org.xerp.deliveryservice.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.xerp.deliveryservice.services.ShortPathStrategy;
import org.xerp.deliveryservice.services.implementation.paths.DijkstraStrategy;

@Configuration
@ComponentScan("org.xerp.deliveryservice.services")
public class ServiceConfiguration {

    @Bean("dijkstra")
    public ShortPathStrategy getDijkstraStrategy() {
        return new DijkstraStrategy();
    }

}
