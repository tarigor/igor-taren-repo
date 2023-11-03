package com.senla.hotelweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@ComponentScan(basePackages = {"com.senla.hotel", "com.senla.hoteldb", "com.senla.hotelio", "com.senla.serialization", "com.senla.hotelweb"})
@EnableJpaRepositories("com.senla.hoteldb.repository")
@EntityScan(basePackages = {"com.senla.hoteldb.entity"})
public class HotelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelWebApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }
}
