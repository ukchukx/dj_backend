package com.geneius.decisionjournal.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdateDecisionJournal implements ValidatedCommand {
  @TargetAggregateIdentifier
  private UUID decisionJournalId;
  private String whatHappened;
}
