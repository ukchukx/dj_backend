package com.geneius.decisionjournal.projectors;

import com.geneius.decisionjournal.entities.Journal;
import com.geneius.decisionjournal.events.DecisionJournalCreated;
import com.geneius.decisionjournal.events.DecisionJournalWhatHappenedChanged;
import com.geneius.decisionjournal.services.JournalService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEventHandler {
  @Autowired
  private JournalService journalService;

  @EventHandler
  public void on(DecisionJournalCreated decisionJournalCreated) {
    Journal journal = new Journal();
    journal.setId(decisionJournalCreated.getDecisionJournalId());
    journal.setAccountId(decisionJournalCreated.getAccountId());
    journal.setIndex(decisionJournalCreated.getIndex());
    journal.setDate(decisionJournalCreated.getDate());
    journal.setReviewDate(decisionJournalCreated.getReviewDate());
    journal.setDecision(decisionJournalCreated.getDecision());
    journal.setSituation(decisionJournalCreated.getSituation());
    journal.setProblemStatement(decisionJournalCreated.getProblemStatement());
    journal.setVariables(decisionJournalCreated.getVariables());
    journal.setComplications(decisionJournalCreated.getComplications());
    journal.setAlternatives(decisionJournalCreated.getAlternatives());
    journal.setOutcomeRange(decisionJournalCreated.getOutcomeRange());
    journal.setExpectations(decisionJournalCreated.getExpectations());
    journal.setOutcome(decisionJournalCreated.getOutcome());
    journal.setWhatHappened(decisionJournalCreated.getWhatHappened());
    journal.setEnergized(decisionJournalCreated.isEnergized());
    journal.setFocused(decisionJournalCreated.isFocused());
    journal.setRelaxed(decisionJournalCreated.isRelaxed());
    journal.setConfident(decisionJournalCreated.isConfident());
    journal.setTired(decisionJournalCreated.isTired());
    journal.setAccepting(decisionJournalCreated.isAccepting());
    journal.setAccommodating(decisionJournalCreated.isAccomodating());
    journal.setAnxious(decisionJournalCreated.isAnxious());
    journal.setResigned(decisionJournalCreated.isResigned());
    journal.setFrustrated(decisionJournalCreated.isFrustrated());
    journal.setAngry(decisionJournalCreated.isAngry());
    journal.setExcited(decisionJournalCreated.isExcited());
    journalService.save(journal);
  }


  @EventHandler
  public void on(DecisionJournalWhatHappenedChanged changed) {
    Journal journal = journalService.getById(changed.getDecisionJournalId()).get();
    journal.setWhatHappened(changed.getWhatHappened());
    journalService.save(journal);
  }
}
