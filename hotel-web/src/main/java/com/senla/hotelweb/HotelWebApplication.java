package com.senla.hotelweb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.senla.hotel",
        "com.senla.hoteldb",
        "com.senla.hotelio",
        "com.senla.serialization",
        "com.senla.hotelweb",
        "com.senla.hotelsecurity"})
@EnableJpaRepositories("com.senla.hoteldb.repository")
@EntityScan(basePackages = {"com.senla.hoteldb.entity"})
public class HotelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelWebApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
