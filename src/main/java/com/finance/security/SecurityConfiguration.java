package com.finance.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.antMatcher("/**").authorizeRequests().antMatchers("/built/**", "/main.css","/", "/login**").permitAll().anyRequest().authenticated()
			.and().logout().logoutSuccessUrl("/").and().formLogin().disable();
	}

	@Bean
	public PrincipalExtractor githubPrincipalExtractor() {
		return new GooglePrincipalExtractor();
	}

}