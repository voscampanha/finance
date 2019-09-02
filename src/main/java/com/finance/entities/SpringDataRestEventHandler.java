package com.finance.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.finance.interfaces.UserAppRepository;

@Component
@RepositoryEventHandler(Account.class)
public class SpringDataRestEventHandler {

	private final UserAppRepository useAppRepository;

	@Autowired
	public SpringDataRestEventHandler(UserAppRepository userAppRepository) {
		this.useAppRepository = userAppRepository;
	}

	@HandleBeforeCreate
	@HandleBeforeSave
	public void applyUserInformationUsingSecurityContext(Account account) {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		UserApp user = this.useAppRepository.findByName(name);
		if (user == null) {
			UserApp newUser = new UserApp();
			newUser.setName(name);
			newUser.setRoles(new String[]{"ROLE_MANAGER"});
			user = this.useAppRepository.save(newUser);
		}
		account.setUser(user);
	}
}