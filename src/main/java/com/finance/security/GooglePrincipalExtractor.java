package com.finance.security;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import com.finance.entities.UserApp;

public class GooglePrincipalExtractor implements PrincipalExtractor {

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		UserApp user = new UserApp(map.get("name").toString(), map.get("email").toString(), "nopass", new String[] {"ROLE_USER"} );
	
		return user;
	}
}