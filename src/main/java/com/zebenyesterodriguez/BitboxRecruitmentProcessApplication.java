package com.zebenyesterodriguez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.zebenyesterodriguez.security.JWTAuthorizationFilter;

@SpringBootApplication
public class BitboxRecruitmentProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitboxRecruitmentProcessApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		@Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http
	            .cors()
	            .and()
	            .csrf().disable()
	            .authorizeRequests()
	            .antMatchers("/login").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .addFilterAfter(
	        		new JWTAuthorizationFilter(),
	                UsernamePasswordAuthenticationFilter.class
	            );
		}
	}	
}
