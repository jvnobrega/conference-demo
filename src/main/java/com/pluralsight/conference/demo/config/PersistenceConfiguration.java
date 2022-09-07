package com.pluralsight.conference.demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:mysql://localhost:3306/conference_demo");
        builder.password("conference_pass");
        builder.username("conference_user");
        System.out.println("My Custom DataSource bean has been initialized and set");
        return builder.build();
    }
}
