package com.finance.interfaces;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import com.finance.entities.Account;

@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	@Override
	@PreAuthorize("#account?.user == null or #account?.user?.name == authentication?.name")
	Account save(@Param("account") Account account);

	@Override
	@PreAuthorize("@accountRepository.findById(#id)?.user?.name == authentication?.name")
	void deleteById(@Param("id") Long id);

	@Override
	@PreAuthorize("#account?.user?.name == authentication?.name")
	void delete(@Param("account") Account account);
}
