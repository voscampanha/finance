package com.finance.resources;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.finance.entities.Account;

@Component
public class AccountResourceMapper {
	private final EntityLinks entityLinks;
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

	@Autowired
	public AccountResourceMapper(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	public AccountResource toResource(Account account) {
		AccountResource resource = new AccountResource(account.getId(), account.getName());
		final Link selfLink = entityLinks.linkToSingleResource(account);
		resource.add(selfLink.withSelfRel());
		resource.add(selfLink.withRel(UPDATE));
		resource.add(selfLink.withRel(DELETE));
		return resource;
	}

	public Collection<AccountResource> toResourceCollection(Collection<Account> domainObjects) {
		return domainObjects.stream().map(t -> toResource(t)).collect(Collectors.toList());
	}

}
