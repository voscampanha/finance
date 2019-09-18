package com.finance.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.finance.entities.Account;
import com.finance.entities.UserApp;
import com.finance.repositories.AccountRepository;
import com.finance.repositories.UserAppRepository;

@Component
public class DataBaseLoader implements CommandLineRunner {

	private final AccountRepository accounts;
	private final UserAppRepository users;

	@Autowired
	public DataBaseLoader(AccountRepository accountRepository, UserAppRepository userRepository) {

		this.accounts = accountRepository;
		this.users = userRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		UserApp vanessa = this.users.save(new UserApp("vanessa", "vos.campanha@gmail.com", "123", "ROLE_USER"));
		UserApp diogo = this.users.save(new UserApp("diogo", "diogonc@gmail.com", "123", "ROLE_USER"));

		try {
		    SecurityContext ctx = SecurityContextHolder.createEmptyContext();
		    SecurityContextHolder.setContext(ctx);
		    
			Authentication authentication = new PreAuthenticatedAuthenticationToken(vanessa, null);
			authentication.setAuthenticated(true);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			this.accounts.save(new Account("Nubank", 1, vanessa));
			this.accounts.save(new Account("CC Banco do Brasil", 1, vanessa));
			this.accounts.save(new Account("PP Banco do Brasil", 1, vanessa));
			
			authentication = new PreAuthenticatedAuthenticationToken(diogo, null);
			authentication.setAuthenticated(true);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			this.accounts.save(new Account("Nubank", 1, diogo));
			this.accounts.save(new Account("Itau", 1, diogo));
			this.accounts.save(new Account("Carteira", 1, diogo));

		} finally {
		    SecurityContextHolder.clearContext();
		}

	}
}