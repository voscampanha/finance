package com.finance.resources;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResource extends ResourceSupport {
	
	private final Long id;
	private final String name;
	
	public AccountResource(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@JsonProperty("id")
	public Long getResourceId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	

}
