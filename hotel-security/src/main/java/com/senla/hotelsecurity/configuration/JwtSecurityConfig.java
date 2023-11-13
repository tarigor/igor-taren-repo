package com.senla.hotelsecurity.configuration;

import com.senla.hotelsecurity.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.senla.hotel.enums.Role.ROLE_ADMIN;
import static com.senla.hotel.enums.Role.ROLE_GUEST;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class JwtSecurityConfig {

    private final AuthenticationEntryPoint authEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public JwtSecurityConfig(@Qualifier("delegatedAuthenticationEntryPoint") AuthenticationEntryPoint authEntryPoint, JwtRequestFilter jwtRequestFilter) {
        this.authEntryPoint = authEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http.cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/any/**").permitAll()
                        .requestMatchers("/api/guest/**").hasAuthority(ROLE_GUEST.name())
                        .requestMatchers(
                                "/api/admin/**",
                                "/bookings/**",
                                "/guests/**",
                                "/guestservices/**",
                                "/rooms/**",
                                "/roomservices/**"
                        ).hasAuthority(ROLE_ADMIN.name()))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
