package com.geneius.decisionjournal.web.controllers;

import com.geneius.decisionjournal.commands.CreateAccount;
import com.geneius.decisionjournal.commands.UpdateAccount;
import com.geneius.decisionjournal.entities.Account;
import com.geneius.decisionjournal.services.AccountService;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.geneius.decisionjournal.web.utils.WebUtil.generateJWT;
import static com.geneius.decisionjournal.web.utils.WebUtil.getData;
import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@RestController
public class AccountController {
  @Autowired
  private AccountService accountService;

  @Autowired
  private ApplicationContext context;

  @RequestMapping(value = "/accounts.signup", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
    Map<String, Object> errMap = new HashMap<>(1);
    CommandBus bus = context.getBean(CommandBus.class);
    CreateAccount command = buildCreateCommand(body);

    if (accountService.findByEmail(command.getEmail()).isPresent()) {
      errMap.put("errorMessage", "Email taken");
      return new ResponseEntity(errMap, HttpStatus.BAD_REQUEST);
    }

    bus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);

    Account account = accountService.getById(command.getAccountId()).get();
    Map<String, Object> map = getAccountMap(account);
    return new ResponseEntity(getData(map), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/accounts.authenticate", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> authenticate(@RequestBody Map<String, String> body) {
    Map<String, Object> errMap = new HashMap<>(1);
    String email = body.getOrDefault("email", "").trim(),
      password = body.getOrDefault("password", "").trim();

    Optional<Account> optional = accountService.findByEmail(email);

    if (!optional.isPresent()) {
      errMap.put("errorMessage", "Account not found");
      return new ResponseEntity(errMap, HttpStatus.FORBIDDEN);
    }

    Account account = optional.get();
    if (!account.isCorrectPassword(password)) {
      errMap.put("errorMessage", "Invalid password");
      return new ResponseEntity(errMap, HttpStatus.FORBIDDEN);
    }
    Map<String, Object> map = getAccountMap(account);
    return new ResponseEntity(getData(map), HttpStatus.OK);
  }

  @Secured("ROLE_USER")
  @RequestMapping(value = "/accounts.currentUser", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> currentUser(Principal principal) {
    if (principal == null) {
      return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    Account account = accountService.getAccountFromPrincipal(principal);
    Map<String, Object> map = getAccountMap(account);
    return new ResponseEntity(getData(map), HttpStatus.OK);
  }

  @Secured("ROLE_USER")
  @RequestMapping(value = "/accounts.updateUser", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> updateUser(@RequestBody Map<String, String> body, Principal principal) {
    if (principal == null) {
      return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    Map<String, Object> errMap = new HashMap<>(1);
    CommandBus bus = context.getBean(CommandBus.class);
    UpdateAccount command = buildUpdateCommand(body);

    Account authUser = accountService.getAccountFromPrincipal(principal);

    if (!authUser.getId().equals(command.getAccountId())) {
      errMap.put("errorMessage", "Can only update own account");
      return new ResponseEntity(errMap, HttpStatus.FORBIDDEN);
    }

    if (!accountService.getById(command.getAccountId()).isPresent()) {
      errMap.put("errorMessage", String.format("Account %s not found", command.getAccountId()));
      return new ResponseEntity(errMap, HttpStatus.BAD_REQUEST);
    }

    if (!command.isValid()) {
      errMap.put("errorMessage", "Bad params given");
      return new ResponseEntity(errMap, HttpStatus.BAD_REQUEST);
    }

    bus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);

    Map<String, Object> map = getAccountMap(accountService.getById(command.getAccountId()).get());
    return new ResponseEntity(getData(map), HttpStatus.OK);
  }

  private Map<String, Object> getAccountMap(Account account) {
    Map<String, Object> map = new HashMap(5);
    map.put("enabled", account.isEnabled());
    map.put("name", account.getName());
    map.put("email", account.getEmail());
    map.put("token", generateJWT(account.getEmail()));
    map.put("id", account.getId());
    return map;
  }

  private UpdateAccount buildUpdateCommand(Map<String, String> map) {
    UpdateAccount command = new UpdateAccount();
    command.setAccountId(UUID.fromString(map.get("id").toString().trim()));
    if (map.containsKey("password")) {
      command.setPassword(map.get("password").toString().trim());
    }

    if (map.containsKey("name")) {
      command.setName(map.get("name").toString().trim());
    }

    if (map.containsKey("disable")) command.setDisabled(true);

    return command;
  }

  private CreateAccount buildCreateCommand(Map<String, String> map) {
    CreateAccount command = new CreateAccount();
    command.setAccountId(UUID.randomUUID());
    if (map.containsKey("password")) {
      command.setPassword(map.get("password").toString().trim());
    }

    if (map.containsKey("name")) {
      command.setName(map.get("name").toString().trim());
    }

    if (map.containsKey("email")) {
      command.setEmail(map.get("email").toString().trim());
    }

    return command;
  }

}
