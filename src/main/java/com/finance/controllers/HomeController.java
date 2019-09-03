package com.finance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
    
//	@RequestMapping("/callback")
//    public String callback() {
//        System.out.println("redirecting to home page");
//        return "/home";
//    }
}
