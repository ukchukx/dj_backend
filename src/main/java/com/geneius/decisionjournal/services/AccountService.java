package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
  List<Account> listAccounts();
  Account save(Account account);
  Optional<Account> findByEmail(String email);
  Optional<Account> getById(UUID id);
  void deleteAll();

  void login(Account user);
  Account getAccountFromPrincipal(Principal principal);
}
