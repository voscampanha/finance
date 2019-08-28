package com.finance.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finance.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {}
