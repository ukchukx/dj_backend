package com.geneius.decisionjournal.projectors;

import com.geneius.decisionjournal.entities.DecisionJournal;
import com.geneius.decisionjournal.events.DecisionJournalCreated;
import com.geneius.decisionjournal.events.DecisionJournalWhatHappenedChanged;
import com.geneius.decisionjournal.services.DecisionJournalService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DecisionJournalEventHandler {
  @Autowired
  private DecisionJournalService decisionJournalService;

  @EventHandler
  public void on(DecisionJournalCreated decisionJournalCreated) {
    DecisionJournal decisionJournal = new DecisionJournal();
    decisionJournal.setId(decisionJournalCreated.getDecisionJournalId());
    decisionJournal.setIndex(decisionJournalCreated.getIndex());
    decisionJournal.setDate(decisionJournalCreated.getDate());
    decisionJournal.setReviewDate(decisionJournalCreated.getReviewDate());
    decisionJournal.setDecision(decisionJournalCreated.getDecision());
    decisionJournal.setSituation(decisionJournalCreated.getSituation());
    decisionJournal.setProblemStatement(decisionJournalCreated.getProblemStatement());
    decisionJournal.setVariables(decisionJournalCreated.getVariables());
    decisionJournal.setComplications(decisionJournalCreated.getComplications());
    decisionJournal.setAlternatives(decisionJournalCreated.getAlternatives());
    decisionJournal.setOutcomeRange(decisionJournalCreated.getOutcomeRange());
    decisionJournal.setExpectations(decisionJournalCreated.getExpectations());
    decisionJournal.setOutcome(decisionJournalCreated.getOutcome());
    decisionJournal.setWhatHappened(decisionJournalCreated.getWhatHappened());
    decisionJournal.setEnergized(decisionJournalCreated.isEnergized());
    decisionJournal.setFocused(decisionJournalCreated.isFocused());
    decisionJournal.setRelaxed(decisionJournalCreated.isRelaxed());
    decisionJournal.setConfident(decisionJournalCreated.isConfident());
    decisionJournal.setTired(decisionJournalCreated.isTired());
    decisionJournal.setAccepting(decisionJournalCreated.isAccepting());
    decisionJournal.setAccommodating(decisionJournalCreated.isAccomodating());
    decisionJournal.setAnxious(decisionJournalCreated.isAnxious());
    decisionJournal.setResigned(decisionJournalCreated.isResigned());
    decisionJournal.setFrustrated(decisionJournalCreated.isFrustrated());
    decisionJournal.setAngry(decisionJournalCreated.isAngry());
    decisionJournal.setExcited(decisionJournalCreated.isExcited());
    decisionJournalService.save(decisionJournal);
  }


  @EventHandler
  public void on(DecisionJournalWhatHappenedChanged changed) {
    DecisionJournal decisionJournal = new DecisionJournal();
    decisionJournal.setId(changed.getDecisionJournalId());
    decisionJournal.setWhatHappened(changed.getWhatHappened());
    decisionJournalService.save(decisionJournal);
  }
}
