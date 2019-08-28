package com.finance.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.finance.entities.Account;
import com.finance.interfaces.AccountRepository;
import com.finance.resources.AccountResource;
import com.finance.resources.AccountResourceMapper;

@RestController
@ExposesResourceFor(Account.class)
@RequestMapping(value = "/account", produces = "application/json")
public class AccountController {
	
	private final AccountRepository repository;
	
	private final AccountResourceMapper mapper;
	
	@Autowired
	public AccountController(AccountRepository repository, AccountResourceMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<AccountResource>> findAll() {
        List<Account> accounts = repository.findAll();
        Collection<AccountResource> resource = mapper.toResourceCollection(accounts);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> findById(@PathVariable Long id) {
        Optional<Account> found = repository.findById(id);

        if (found.isPresent()) {
            return ResponseEntity.ok(mapper.toResource(found.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<AccountResource> create(@RequestBody Account account) {
    	Account createdAccount = repository.save(account);
        AccountResource resource = mapper.toResource(createdAccount);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<AccountResource> update(@PathVariable Long id, @RequestBody Account toUpdate) {
        Optional<Account> found = repository.findById(id);
        if (found.isPresent()) {
        	Account updated = repository.save(toUpdate);
            return ResponseEntity.ok(mapper.toResource(updated));
        }
        return ResponseEntity.notFound().build();
    }
	
}
