package org.xerp.deliveryservice.ioc;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class OrmConfiguration {

    @Bean
    public SessionFactory getSessionFactory() {
        var dbProperties = new Properties();
        SessionFactory sessionFactory = null;

        try {
            dbProperties.load(new FileInputStream("db.properties"));
        } catch (IOException e) {
        }

        var registry = new StandardServiceRegistryBuilder()
                .applySettings(dbProperties)
                .configure()
                .build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        return sessionFactory;
    }
}
