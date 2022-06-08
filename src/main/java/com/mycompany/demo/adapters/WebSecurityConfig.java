package com.mycompany.demo.adapters;

import com.mycompany.demo.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/dashboard/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/dashboard/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/dashboard/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/dashboard/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/hello").permitAll()
				.antMatchers("/pizza").permitAll()
				.anyRequest().authenticated()
				.and().httpBasic()
				.and().cors().disable().csrf().disable();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
