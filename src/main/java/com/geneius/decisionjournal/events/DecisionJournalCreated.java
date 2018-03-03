package com.geneius.decisionjournal.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionJournalCreated {
  private String decisionJournalId;
  private String accountId;
  private String index;
  private long date;
  private long reviewDate;
  private String decision;
  private String situation;
  private String problemStatement;
  private String variables;
  private String complications;
  private String alternatives;
  private String outcomeRange;
  private String expectations;
  private String outcome;
  private String whatHappened;
  private boolean energized;
  private boolean focused;
  private boolean relaxed;
  private boolean confident;
  private boolean tired;
  private boolean accepting;
  private boolean accomodating;
  private boolean anxious;
  private boolean resigned;
  private boolean frustrated;
  private boolean angry;
  private boolean excited;
}
