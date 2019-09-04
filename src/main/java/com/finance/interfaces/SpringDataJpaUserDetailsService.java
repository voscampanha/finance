package com.finance.interfaces;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.finance.entities.UserApp;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final UserAppRepository repository;

	@Autowired
	public SpringDataJpaUserDetailsService(UserAppRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		UserApp user = this.repository.findByName(name);
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles()[0]);
		return new User(user.getName(), user.getPassword(), Arrays.asList(authority));
	}

}