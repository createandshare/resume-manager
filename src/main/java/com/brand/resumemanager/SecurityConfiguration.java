package com.brand.resumemanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.headers(headers -> headers.frameOptions(op -> op.disable()))
			.authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll())
			.formLogin(form -> form.permitAll())
	        .csrf(csrf -> csrf.disable());

		return http.build();
	}
}
