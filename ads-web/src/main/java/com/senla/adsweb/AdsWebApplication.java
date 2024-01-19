package com.senla.adsweb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.senla.adsservice",
        "com.senla.adsdatabase",
        "com.senla.adsemailservice",
        "com.senla.adssecurity",
        "com.senla.adsweb"})
@EnableJpaRepositories("com.senla.adsdatabase.repository")
@EntityScan(basePackages = {"com.senla.adsdatabase.entity"})
public class AdsWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdsWebApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}