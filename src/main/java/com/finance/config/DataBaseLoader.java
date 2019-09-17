package com.finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.finance.entities.Account;
import com.finance.entities.UserApp;
import com.finance.repositories.AccountRepository;
import com.finance.repositories.UserAppRepository;

//@Component
public class DataBaseLoader implements CommandLineRunner {
//	@Autowired
//	UserDetailsServiceImpl userDetailsService;

	private final AccountRepository accounts;
	private final UserAppRepository users;

	@Autowired
	public DataBaseLoader(AccountRepository accountRepository, UserAppRepository userRepository) {

		this.accounts = accountRepository;
		this.users = userRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		UserApp vanessa = this.users.save(new UserApp("vanessa", "vos.campanha@gmail.com", "123", "ROLE_ADMIN"));
		UserApp diogo = this.users.save(new UserApp("diogo", "diogonc@gmail.com", "123", "ROLE_USER"));

		// Do what ever you want to do
		this.accounts.save(new Account("Nubank", 1, vanessa));
		this.accounts.save(new Account("CC Banco do Brasil", 1, vanessa));
		this.accounts.save(new Account("PP Banco do Brasil", 1, vanessa));

		this.accounts.save(new Account("Nubank", 1, diogo));
		this.accounts.save(new Account("Itau", 1, diogo));
		this.accounts.save(new Account("Carteira", 1, diogo));

	}
}