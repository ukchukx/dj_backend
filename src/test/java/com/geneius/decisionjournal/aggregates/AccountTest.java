package com.geneius.decisionjournal.aggregates;

import com.geneius.decisionjournal.commands.CreateAccount;
import com.geneius.decisionjournal.commands.UpdateAccount;
import com.geneius.decisionjournal.events.AccountCreated;
import com.geneius.decisionjournal.events.AccountDisabled;
import com.geneius.decisionjournal.events.AccountNameChanged;
import com.geneius.decisionjournal.events.AccountPasswordChanged;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class AccountTest {
  private FixtureConfiguration<Account> fixture;

  private CreateAccount buildCreateAccountCommand() {
    CreateAccount createAccount = new CreateAccount();
    createAccount.setAccountId(UUID.randomUUID());
    createAccount.setName("Test User");
    createAccount.setEmail("test@user.com");
    createAccount.setPassword("password");
    return createAccount;
  }

  private AccountCreated buildAccountCreatedEvent(CreateAccount createAccount) {
    AccountCreated accountCreated = new AccountCreated();
    accountCreated.setAccountId(createAccount.getAccountId());
    accountCreated.setName(createAccount.getName());
    accountCreated.setPassword(createAccount.getPassword());
    accountCreated.setEmail(createAccount.getEmail());
    accountCreated.setEnabled(createAccount.isEnabled());
    return accountCreated;
  }

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture(Account.class);
  }

  @Test
  public void createAccountShouldReturnAccountCreated() throws Exception {
    CreateAccount createAccount = buildCreateAccountCommand();
    AccountCreated accountCreated = buildAccountCreatedEvent(createAccount);

    fixture.givenNoPriorActivity()
        .when(createAccount)
        .expectEvents(accountCreated);
  }

  @Test
  public void updateAccountShouldSucceed() throws Exception {
    AccountCreated accountCreated = buildAccountCreatedEvent(buildCreateAccountCommand());
    UpdateAccount updateAccount = new UpdateAccount();
    updateAccount.setAccountId(accountCreated.getAccountId());
    updateAccount.setName("New name");
    updateAccount.setPassword("newPassword");
    updateAccount.setDisabled(true);

    AccountNameChanged accountNameChanged = new AccountNameChanged(accountCreated.getAccountId(),
        updateAccount.getName());
    AccountPasswordChanged accountPasswordChanged = new AccountPasswordChanged(accountCreated.getAccountId(),
        updateAccount.getPassword());

    AccountDisabled accountDisabled = new AccountDisabled(accountCreated.getAccountId());

    fixture.given(accountCreated)
        .when(updateAccount)
        .expectEvents(accountNameChanged, accountPasswordChanged, accountDisabled);
  }
}
