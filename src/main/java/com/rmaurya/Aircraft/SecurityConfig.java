package com.rmaurya.Aircraft;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //encoder to encode the password -BCRYPT Encoding
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    UserDetailsService authentication(){

        UserDetails us1 = User.builder().username("ravi").password(passwordEncoder.encode( "ravi")).roles("USER").build();
        UserDetails us2 = User.builder().username("qcom").password(passwordEncoder.encode("qcom")).roles("USER","ADMIN").build();
        System.out.println(us1.getUsername()+"  "+us1.getPassword());
        return new InMemoryUserDetailsManager(us1,us2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(
                auth->auth.requestMatchers("/aircraftadmin/**")
                        .hasRole("ADMIN").anyRequest().authenticated())
                        .formLogin(withDefaults()).httpBasic(withDefaults());

        return httpSecurity.build();

    }

}
