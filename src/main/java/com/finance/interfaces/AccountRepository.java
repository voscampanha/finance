package com.finance.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.finance.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {}
