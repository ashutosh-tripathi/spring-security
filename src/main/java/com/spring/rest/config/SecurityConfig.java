package com.spring.rest.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {
	

	/*
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler= new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");
        http
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    	CorsConfiguration config= new CorsConfiguration();
                    	config.setAllowedOrigins(Collections.singletonList("*"));
                    	config.setAllowedMethods(Collections.singletonList("*"));
                    	config.setAllowCredentials(true);
                    	config.setAllowedHeaders(Collections.singletonList("*"));
                    	return config;
                    }
                }))
                .csrf((csrf)-> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/addUsers")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/Account").hasRole("ADMIN")
                                .requestMatchers("/Balance","/Cards").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/Account", "/Balance", "/Cards", "/Loans").authenticated()
                                .requestMatchers("/Contact", "/Notices", "/addUsers","/h2-console").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
		return http.build();
	}*/
	
	
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler= new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");


        http
                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("*"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Collections.singletonList("Authorization"));
                        return config;
                    }
                }))
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/addUsers")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
           
                .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)
               
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/Account").hasRole("ADMIN")
                                .requestMatchers("/Balance", "/Cards").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/Account", "/Balance", "/Cards", "/Loans").authenticated()
                                .requestMatchers("/Contact", "/Notices", "/addUsers", "/h2-console").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
		return http.build();
	}
	
	
	
	
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource datasource)
//	{
//		
//		return new JdbcUserDetailsManager(datasource);
//	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new  BCryptPasswordEncoder();
	}

}
