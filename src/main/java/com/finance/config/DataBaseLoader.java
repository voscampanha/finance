package com.finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.finance.entities.Account;
import com.finance.entities.UserApp;
import com.finance.interfaces.AccountRepository;
import com.finance.interfaces.UserAppRepository;

@Component
public class DataBaseLoader implements CommandLineRunner {

	private final AccountRepository accounts;
	private final UserAppRepository users;

	@Autowired
	public DataBaseLoader(AccountRepository accountRepository,
			UserAppRepository userRepository) {

		this.accounts = accountRepository;
		this.users = userRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		UserApp greg = this.users.save(new UserApp("greg", "turnquist",
							"ROLE_MANAGER"));
		UserApp oliver = this.users.save(new UserApp("oliver", "gierke",
							"ROLE_MANAGER"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("greg", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.accounts.save(new Account("Frodo", 0, "ring bearer", greg));
		this.accounts.save(new Account("Bilbo", 0, "burglar", greg));
		this.accounts.save(new Account("Gandalf", 0, "wizard", greg));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("oliver", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.accounts.save(new Account("Samwise", 0, "gardener", oliver));
		this.accounts.save(new Account("Merry", 0, "pony rider", oliver));
		this.accounts.save(new Account("Peregrin", 0, "pipe smoker", oliver));

		SecurityContextHolder.clearContext();
	}
}