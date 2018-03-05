package com.geneius.decisionjournal.aggregates;

import com.geneius.decisionjournal.aggregates.exceptions.InvalidCommandException;
import com.geneius.decisionjournal.commands.CreateDecisionJournal;
import com.geneius.decisionjournal.commands.UpdateDecisionJournal;
import com.geneius.decisionjournal.events.DecisionJournalCreated;
import com.geneius.decisionjournal.events.DecisionJournalWhatHappenedChanged;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Entity
public class DecisionJournal {
  @Id
  private UUID decisionJournalId;
  private UUID accountId;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String index;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String decision;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String situation;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String problemStatement;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String variables;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String complications;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String alternatives;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String outcomeRange;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String expectations;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String outcome;
  @Basic
  @Column(columnDefinition = "TEXT", nullable = false)
  private String whatHappened;
  @Basic
  private boolean energized;
  @Basic
  private boolean focused;
  @Basic
  private boolean relaxed;
  @Basic
  private boolean confident;
  @Basic
  private boolean tired;
  @Basic
  private boolean accepting;
  @Basic
  private boolean accomodating;
  @Basic
  private boolean anxious;
  @Basic
  private boolean resigned;
  @Basic
  private boolean frustrated;
  @Basic
  private boolean angry;
  @Basic
  private boolean excited;
  @Basic
  private long date;
  @Basic
  private long reviewDate;


  @CommandHandler
  public DecisionJournal(CreateDecisionJournal create) throws InvalidCommandException {
    if (create.isValid()) {
      DecisionJournalCreated created = new DecisionJournalCreated();
      created.setDecisionJournalId(create.getDecisionJournalId());
      created.setAccountId(create.getAccountId());
      created.setIndex(create.getIndex());
      created.setDate(create.getDate());
      created.setReviewDate(create.getReviewDate());
      created.setDecision(create.getDecision());
      created.setSituation(create.getSituation());
      created.setProblemStatement(create.getProblemStatement());
      created.setVariables(create.getVariables());
      created.setComplications(create.getComplications());
      created.setAlternatives(create.getAlternatives());
      created.setOutcomeRange(create.getOutcomeRange());
      created.setExpectations(create.getExpectations());
      created.setOutcome(create.getOutcome());
      created.setWhatHappened(create.getWhatHappened());
      created.setEnergized(create.isEnergized());
      created.setFocused(create.isFocused());
      created.setRelaxed(create.isRelaxed());
      created.setConfident(create.isConfident());
      created.setTired(create.isTired());
      created.setAccepting(create.isAccepting());
      created.setAccomodating(create.isAccommodating());
      created.setAnxious(create.isAnxious());
      created.setResigned(create.isResigned());
      created.setFrustrated(create.isFrustrated());
      created.setAngry(create.isAngry());
      created.setExcited(create.isExcited());

      apply(created);
    } else {
      throw new InvalidCommandException();
    }
  }

  @CommandHandler
  public void handle(UpdateDecisionJournal updateDecisionJournal) throws InvalidCommandException {
    if (updateDecisionJournal.isValid()) {
      if (this.whatHappened.isEmpty() &&
          updateDecisionJournal.getWhatHappened() != null &&
          !updateDecisionJournal.getWhatHappened().isEmpty()) {
        DecisionJournalWhatHappenedChanged changed = new DecisionJournalWhatHappenedChanged(
            updateDecisionJournal.getDecisionJournalId(),
            updateDecisionJournal.getWhatHappened());

        apply(changed);
      }
    } else {
      throw new InvalidCommandException();
    }
  }

  @EventSourcingHandler
  public void on(DecisionJournalCreated created) {
    decisionJournalId = created.getDecisionJournalId();
    accountId = created.getAccountId();
    index = created.getIndex();
    date = created.getDate();
    reviewDate = created.getReviewDate();
    decision = created.getDecision();
    situation = created.getSituation();
    problemStatement = created.getProblemStatement();
    variables = created.getVariables();
    complications = created.getComplications();
    alternatives = created.getAlternatives();
    outcomeRange = created.getOutcomeRange();
    expectations = created.getExpectations();
    outcome = created.getOutcome();
    whatHappened = created.getWhatHappened();
    energized = created.isEnergized();
    focused = created.isFocused();
    relaxed = created.isRelaxed();
    confident = created.isConfident();
    tired = created.isTired();
    accepting = created.isAccepting();
    accomodating = created.isAccomodating();
    anxious = created.isAnxious();
    resigned = created.isResigned();
    frustrated = created.isFrustrated();
    angry = created.isAngry();
    excited = created.isExcited();
  }


  @EventSourcingHandler
  public void on(DecisionJournalWhatHappenedChanged changed) {
    whatHappened = changed.getWhatHappened();
  }

}
