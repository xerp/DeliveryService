package org.xerp.deliveryservice.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.xerp.deliveryservice.repositories")
public class RepositoryConfiguration {
}
