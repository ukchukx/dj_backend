package com.geneius.decisionjournal.projectors;

import com.geneius.decisionjournal.entities.Account;
import com.geneius.decisionjournal.events.AccountCreated;
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
    Account account = new Account();
    account.setId(accountCreated.getAccountId());
    account.setEmail(accountCreated.getEmail());
    account.setName(accountCreated.getName());
    account.setPassword(accountCreated.getPassword());
    accountService.save(account);
  }

  @EventHandler
  public void on(AccountNameChanged accountNameChanged) {
    Account account = new Account();
    account.setId(accountNameChanged.getAccountId());
    account.setName(accountNameChanged.getName());
    accountService.save(account);
  }

  @EventHandler
  public void on(AccountPasswordChanged accountPasswordChanged) {
    Account account = new Account();
    account.setId(accountPasswordChanged.getAccountId());
    account.setPassword(accountPasswordChanged.getPassword());
    accountService.save(account);
  }
}
