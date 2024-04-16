package com.equipo3.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//La anotación configuration indica que una clase tiene una o más definiciones de Bean
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class DefaultSecurityConfig {
  //Bean objeto que puede utilizarse para proporcionar servicios o funcionalidades a otras partes

  //Agrega filtros a los diferentes recursos de la aplicación
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(Customizer.withDefaults())
      .httpBasic(Customizer.withDefaults())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .formLogin(Customizer.withDefaults())
      .build();
  }

  //Define como spring-security va a llevar a cabo la autenticación
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  //Define un proveedor que se encargará de velar por la autenticación
  @Bean
  public AuthenticationProvider authenticationProvider() {
    //Proveedor que permite autenticarnos en un acceso a datos (DAO -> Data Access Object).
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //Agregamos las validaciones para la autenticación
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailsService());
    return provider;
  }

  //Define como se cargan los datos desde la fuente de datos
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails userDetails = User.withUsername("admin")
      .password("$2a$10$kqlHu07r1WYEZSGxXGrX3ukh2UT3lZ.Y2P5OZwjajrxqP/TR/s9GK")
      .roles("USER")
      .build();

    return new InMemoryUserDetailsManager(userDetails);
  }

  //Define cómo se va a encriptar la contraseña
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
