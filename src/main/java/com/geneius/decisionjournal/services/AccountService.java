package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
  List<Account> listAccounts();
  Account save(Account account);
  Optional<Account> findByEmail(String email);
  Optional<Account> getById(String id);
}
