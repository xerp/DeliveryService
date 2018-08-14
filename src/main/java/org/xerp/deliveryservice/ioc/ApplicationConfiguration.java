package org.xerp.deliveryservice.ioc;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.PrintStream;

@Configuration
@ComponentScan("org.xerp.deliveryservice")
@Import({ServiceConfiguration.class})
public class ApplicationConfiguration {

    @Bean
    public PrintStream getConsole() {
        return System.out;
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
