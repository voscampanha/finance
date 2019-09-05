package com.finance.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.finance.entities.Account;

@Component("beforeCreateAccountValidator")
public class AccountValidator implements Validator {

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
	}

}
