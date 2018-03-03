package com.geneius.decisionjournal.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdateDecisionJournal implements ValidatedCommand {
  @TargetAggregateIdentifier
  private String decisionJournalId;
  private String whatHappened;


  public void setDecisionJournalId(String decisionJournalId) {
    this.decisionJournalId = decisionJournalId;
  }

  public void setDecisionJournalId(UUID decisionJournalId) {
    this.decisionJournalId = decisionJournalId.toString();
  }
}
