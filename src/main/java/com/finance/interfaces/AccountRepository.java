package com.finance.interfaces;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finance.entities.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {}
