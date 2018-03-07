package com.geneius.decisionjournal.projectors;

import com.geneius.decisionjournal.entities.Account;
import com.geneius.decisionjournal.events.AccountCreated;
import com.geneius.decisionjournal.events.AccountDisabled;
import com.geneius.decisionjournal.events.AccountNameChanged;
import com.geneius.decisionjournal.events.AccountPasswordChanged;
import com.geneius.decisionjournal.services.AccountService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandler {
  @Autowired
  private AccountService accountService;


  @EventHandler
  public void on(AccountCreated accountCreated) {
    if (accountService.getById(accountCreated.getAccountId()).isPresent()) {
      return;
    }

    Account account = new Account();
    account.setId(accountCreated.getAccountId());
    account.setEmail(accountCreated.getEmail());
    account.setName(accountCreated.getName());
    account.setPassword(accountCreated.getPassword());
    account.setEnabled(accountCreated.isEnabled());
    accountService.save(account);
  }

  @EventHandler
  public void on(AccountNameChanged accountNameChanged) {
    Account account = accountService.getById(accountNameChanged.getAccountId()).get();
    account.setName(accountNameChanged.getName());
    accountService.save(account);
  }

  @EventHandler
  public void on(AccountPasswordChanged accountPasswordChanged) {
    Account account = accountService.getById(accountPasswordChanged.getAccountId()).get();
    account.setPassword(accountPasswordChanged.getPassword());
    accountService.save(account);
  }

  @EventHandler
  public void on(AccountDisabled accountDisabled) {
    Account account = accountService.getById(accountDisabled.getAccountId()).get();
    account.setEnabled(false);
    accountService.save(account);
  }
}
