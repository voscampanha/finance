package com.finance.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finance.entities.Account;

//@PreAuthorize("hasRole('ROLE_MANAGER')")
@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
	@Query("SELECT a FROM Account a WHERE a.name = ?1 AND a.user.id = ?2")
    Account findByNameAndUser(String name, Long userId);
	
//	@Override
//	@PreAuthorize("#account?.user == null or #account?.user?.name == authentication?.name")
//	Account save(@Param("account") Account account);
//
//	@Override
//	@PreAuthorize("@accountRepository.findById(#id)?.user?.name == authentication?.name")
//	void deleteById(@Param("id") Long id);
//
//	@Override
//	@PreAuthorize("#account?.user?.name == authentication?.name")
//	void delete(@Param("account") Account account);
}
