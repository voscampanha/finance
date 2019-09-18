package com.finance.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.finance.entities.Account;

@PreAuthorize("hasRole('ROLE_USER')")
@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.name = ?1 AND a.user.id = ?2")
    Account findByNameAndUser(String name, Long userId);
	
	@Override
	@PreAuthorize("#account?.id == null or @accountRepository.findById(#account?.id)?.user?.email == authentication?.principal.email")
	Account save(@Param("account") Account account);
//
//	@Override
//	@PreAuthorize("@accountRepository.findById(#id)?.user?.name == authentication?.name")
//	void deleteById(@Param("id") Long id);
//
//	@Override
//	@PreAuthorize("#account?.user?.name == authentication?.name")
//	void delete(@Param("account") Account account);
}
