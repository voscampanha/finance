package com.finance.controllers;

import java.security.Principal;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
public class HomeController {
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
    
}
