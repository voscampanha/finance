package com.finance.interfaces;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.finance.entities.UserApp;

@RepositoryRestResource(exported = false)
public interface UserAppRepository extends Repository<UserApp, Long> {

	UserApp save(UserApp user);

	UserApp findByName(String name);

}