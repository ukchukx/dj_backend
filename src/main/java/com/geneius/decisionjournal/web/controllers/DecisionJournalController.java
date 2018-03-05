package com.geneius.decisionjournal.web.controllers;

import com.geneius.decisionjournal.commands.CreateDecisionJournal;
import com.geneius.decisionjournal.commands.UpdateDecisionJournal;
import com.geneius.decisionjournal.entities.Account;
import com.geneius.decisionjournal.entities.DecisionJournal;
import com.geneius.decisionjournal.services.AccountService;
import com.geneius.decisionjournal.services.DecisionJournalService;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.geneius.decisionjournal.web.utils.WebUtil.getData;
import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@RestController
@Secured("ROLE_USER")
public class DecisionJournalController {
  @Autowired
  private DecisionJournalService decisionJournalService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private ApplicationContext context;

  @RequestMapping(value = "/decisionJournals.list", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> list(Principal principal) {
    Account account = accountService.getAccountFromPrincipal(principal);
    return new ResponseEntity(getData(decisionJournalService.listByAccount(account.getId())), HttpStatus.OK);
  }

  @RequestMapping(value = "/decisionJournals.get", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> getDecisionJournal(@RequestBody Map<String, String> body) {
    String id = body.getOrDefault("id", "").toString();
    Optional<DecisionJournal> optional = decisionJournalService.getById(UUID.fromString(id));
    if (optional.isPresent()) {
      return new ResponseEntity(getData(optional.get()), HttpStatus.OK);
    } else {
      Map<String, Object> errMap = new HashMap<>(1);
      errMap.put("errorMessage", String.format("Decision journal %d not found", id));
      return new ResponseEntity(errMap, HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/decisionJournals.create", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> create(@RequestBody Map<String, Object> body, Principal principal) {
    Map<String, Object> errMap = new HashMap<>(1);
    Account account = accountService.getAccountFromPrincipal(principal);
    body.put("accountId", account.getId());

    CommandBus bus = context.getBean(CommandBus.class);
    CreateDecisionJournal command = buildCreateCommand(body);

    if (decisionJournalService.getByIdAndIndex(command.getDecisionJournalId(), command.getIndex()).isPresent()) {
      errMap.put("errorMessage", String.format("Index already used"));
      return new ResponseEntity(errMap, HttpStatus.BAD_REQUEST);
    }

    bus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);

    DecisionJournal journal = decisionJournalService.getById(command.getDecisionJournalId()).get();
    return new ResponseEntity(getData(journal), HttpStatus.OK);
  }

  @RequestMapping(value = "/decisionJournals.updateWhatHappened", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> updateWhatHappened(@RequestBody Map<String, String> body) {
    String id = body.getOrDefault("id", "").toString();
    Optional<DecisionJournal> optional = decisionJournalService.getById(UUID.fromString(id));

    if (optional.isPresent()) {
      String whatHappened = body.getOrDefault("whatHappened", "").toString();
      DecisionJournal journal = optional.get();

      if (!journal.getWhatHappened().isEmpty() || whatHappened == null || whatHappened.trim().isEmpty()) {
        return new ResponseEntity(getData(journal), HttpStatus.OK);
      }

      CommandBus bus = context.getBean(CommandBus.class);
      UpdateDecisionJournal command = new UpdateDecisionJournal();
      command.setDecisionJournalId(journal.getId());
      command.setWhatHappened(whatHappened);
      bus.dispatch(asCommandMessage(command), LoggingCallback.INSTANCE);

      journal = decisionJournalService.getById(UUID.fromString(id)).get();
      return new ResponseEntity(getData(journal), HttpStatus.OK);
    } else {
      Map<String, Object> errMap = new HashMap<>(1);
      errMap.put("errorMessage", String.format("Decision journal %d not found", id));
      return new ResponseEntity(errMap, HttpStatus.NOT_FOUND);
    }
  }

  private CreateDecisionJournal buildCreateCommand(Map<String, Object> map) {
    CreateDecisionJournal command = new CreateDecisionJournal();
    command.setDecisionJournalId(UUID.randomUUID());
    command.setAccountId(UUID.fromString(map.get("accountId").toString().trim()));
    command.setIndex(map.get("index").toString().trim());
    command.setDate(Long.parseLong(map.get("date").toString().trim()));
    command.setReviewDate(Long.parseLong(map.get("reviewDate").toString().trim()));
    command.setDecision(map.getOrDefault("decision","").toString().trim());
    command.setSituation(map.getOrDefault("situation","").toString().trim());
    command.setProblemStatement(map.getOrDefault("problemStatement","").toString().trim());
    command.setVariables(map.getOrDefault("variables","").toString().trim());
    command.setComplications(map.getOrDefault("complications","").toString().trim());
    command.setAlternatives(map.getOrDefault("alternatives","").toString().trim());
    command.setOutcomeRange(map.getOrDefault("outcomeRange","").toString().trim());
    command.setExpectations(map.getOrDefault("expectations","").toString().trim());
    command.setOutcome(map.getOrDefault("outcome", "").toString().trim());
    command.setEnergized(Boolean.parseBoolean(map.getOrDefault("energized", "false").toString().trim()));
    command.setFocused(Boolean.parseBoolean(map.getOrDefault("focused", "false").toString().trim()));
    command.setRelaxed(Boolean.parseBoolean(map.getOrDefault("relaxed", "false").toString().trim()));
    command.setConfident(Boolean.parseBoolean(map.getOrDefault("confident", "false").toString().trim()));
    command.setTired(Boolean.parseBoolean(map.getOrDefault("tired", "false").toString().trim()));
    command.setAccepting(Boolean.parseBoolean(map.getOrDefault("accepting", "false").toString().trim()));
    command.setAccommodating(Boolean.parseBoolean(map.getOrDefault("accommodating", "false").toString().trim()));
    command.setAnxious(Boolean.parseBoolean(map.getOrDefault("anxious", "false").toString().trim()));
    command.setResigned(Boolean.parseBoolean(map.getOrDefault("resigned", "false").toString().trim()));
    command.setFrustrated(Boolean.parseBoolean(map.getOrDefault("frustrated", "false").toString().trim()));
    command.setAngry(Boolean.parseBoolean(map.getOrDefault("angry", "false").toString().trim()));
    command.setExcited(Boolean.parseBoolean(map.getOrDefault("excited", "false").toString().trim()));
    return command;
  }

}
