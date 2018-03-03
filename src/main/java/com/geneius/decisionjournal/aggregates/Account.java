package com.geneius.decisionjournal.aggregates;

import com.geneius.decisionjournal.aggregates.exceptions.InvalidCommandException;
import com.geneius.decisionjournal.commands.CreateAccount;
import com.geneius.decisionjournal.commands.UpdateAccount;
import com.geneius.decisionjournal.events.AccountCreated;
import com.geneius.decisionjournal.events.AccountNameChanged;
import com.geneius.decisionjournal.events.AccountPasswordChanged;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.HashSet;
import java.util.Set;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Entity
public class Account {
  @Id
  private String accountId;
  @Basic
  private String email;
  @Basic
  private String name;
  @Basic
  private String password;

  private static Set<String> emailSet;

  static {
    emailSet = new HashSet(50);
  }

  @CommandHandler
  public Account(CreateAccount createAccount) throws InvalidCommandException {
    if (createAccount.isValid()) {
      AccountCreated accountCreated = new AccountCreated();
      accountCreated.setAccountId(createAccount.getAccountId());
      accountCreated.setEmail(createAccount.getEmail());
      accountCreated.setPassword(createAccount.getPassword());
      accountCreated.setName(createAccount.getName());

      apply(accountCreated);
    } else {
      throw new InvalidCommandException();
    }
  }

  @CommandHandler
  public void handle(UpdateAccount updateAccount) throws InvalidCommandException {
    if (updateAccount.isValid()) {
      String newName = updateAccount.getName(),
              newPassword = updateAccount.getPassword();
      if (newName != null && !newName.equals(this.name)) {
        apply(new AccountNameChanged(this.accountId, newName));
      }
      if (newPassword != null && !newPassword.equals(this.password)) {
        apply(new AccountPasswordChanged(this.accountId, newPassword));
      }
    } else {
      throw new InvalidCommandException();
    }
  }

  @EventSourcingHandler
  public void on(AccountCreated accountCreated) {
    accountId = accountCreated.getAccountId();
    email = accountCreated.getEmail();
    password = accountCreated.getPassword();
    name = accountCreated.getName();
  }

  @EventSourcingHandler
  public void on(AccountNameChanged accountNameChanged) {
    name = accountNameChanged.getName();
  }

  @EventSourcingHandler
  public void on(AccountPasswordChanged accountPasswordChanged) {
    password = accountPasswordChanged.getPassword();
  }
}
