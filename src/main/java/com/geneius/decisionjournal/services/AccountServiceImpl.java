package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Account;
import com.geneius.decisionjournal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public List<Account> listAccounts() {
    return accountRepository.findAll();
  }

  @Override
  public Account save(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Optional<Account> findByEmail(String email) {
    return accountRepository.findByEmail(email);
  }

  @Override
  public Optional<Account> getById(String id) {
    return accountRepository.findById(id);
  }
}
