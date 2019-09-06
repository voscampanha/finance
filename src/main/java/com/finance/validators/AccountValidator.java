package com.finance.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.finance.entities.Account;
import com.finance.repositories.UserAppRepository;

@Component("beforeCreateAccountValidator")
public class AccountValidator implements Validator {
	
	private final UserAppRepository repository;

	@Autowired
	public AccountValidator(UserAppRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		final Account account = (Account) target;
		if (StringUtils.isEmpty(account.getName())) {
			errors.rejectValue("name", "name.empty");
		}
		if (StringUtils.isEmpty(account.getPriority()) || 1 > account.getPriority()) {
			errors.rejectValue("priority", "priority.invalid");
		}
		if (account.getUser() == null || account.getUser().getId() == null || repository.findById(account.getUser().getId()) == null) {
			errors.rejectValue("user", "user.invalid");
		}
	}

}
