package com.geneius.decisionjournal.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionJournalWhatHappenedChanged {
  private UUID decisionJournalId;
  private String whatHappened;
}
