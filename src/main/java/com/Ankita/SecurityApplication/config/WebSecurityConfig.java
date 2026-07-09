package com.Ankita.SecurityApplication.config;

import com.Ankita.SecurityApplication.filters.JwtAuthFilter;
import com.Ankita.SecurityApplication.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.Ankita.SecurityApplication.entities.enums.Role.ADMIN;
import static com.Ankita.SecurityApplication.entities.enums.Role.CREATOR;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] publicRoutes ={
            "/posts","/error","/auth/**","/home.html"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(HttpMethod.GET,"/posts/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/posts/**")
                        .hasAnyRole(ADMIN.name(),CREATOR.name())
                        .anyRequest().authenticated())
                 .csrf(csrfConfig->csrfConfig.disable())
                 .sessionManagement(sessionConfig->sessionConfig
                         .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                 .oauth2Login(oauth2conig-> oauth2conig
                 .failureUrl("/login?error=true")
                 .successHandler(oAuth2SuccessHandler)
                 );
//                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    @Primary
//    UserDetailsService myInMemoryUserDetailsService(){
//        UserDetails normalUser= User
//                                 .withUsername("Ankita")
//                                 .password(passwordEncoder().encode("Abc@123"))
//                                 .roles("USER")
//                                 .build();
//
//        UserDetails adminUser=User
//                                  .withUsername("admin")
//                                  .password(passwordEncoder().encode("admin"))
//                                  .roles("ADMIN")
//                                  .build();
//
//        return new InMemoryUserDetailsManager(normalUser,adminUser);
//
//    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
