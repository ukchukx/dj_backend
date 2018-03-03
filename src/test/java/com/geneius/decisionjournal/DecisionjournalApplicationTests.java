package com.geneius.decisionjournal;

import com.geneius.decisionjournal.commands.CreateAccount;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.UUID;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DecisionjournalApplicationTests {
	@Autowired
  private ApplicationContext context;

	private CreateAccount buildCreateAccountCommand() {
    CreateAccount createAccount = new CreateAccount();
    createAccount.setAccountId(UUID.randomUUID());
    createAccount.setName("Test User");
    createAccount.setEmail("test@user.com");
    createAccount.setPassword("password");
    return createAccount;
  }

  @Test
  public void startingUpTheApplicationContextSucceeds() throws SQLException {
    assertNotNull("Application context not initialised successfully", context);
  }

  @Test
  public void startingUpTheApplicationContextWithCommandBusSucceeds() throws SQLException {
    CommandBus commandBus = context.getBean(CommandBus.class);
    assertNotNull("Application context not initialised successfully", context);
    assertNotNull("Application CommandBus not initialised successfully", commandBus);

    commandBus.dispatch(asCommandMessage(buildCreateAccountCommand()));
  }

  @Test
  public void testMethodForCheckingCommands() throws Exception {
    CommandBus commandBus = context.getBean(CommandBus.class);

    commandBus.dispatch(asCommandMessage(buildCreateAccountCommand()), LoggingCallback.INSTANCE);
    commandBus.dispatch(asCommandMessage(buildCreateAccountCommand()), LoggingCallback.INSTANCE);

  }

}
