package com.geneius.decisionjournal.commands;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateDecisionJournal implements ValidatedCommand {
  private String decisionJournalId;
  private String accountId;
  private String index;
  private String decision;
  private String situation;
  private String problemStatement;
  private String variables;
  private String complications;
  private String alternatives;
  private String outcomeRange;
  private String expectations;
  private String outcome;
  private String whatHappened = "";
  private boolean energized;
  private boolean focused;
  private boolean relaxed;
  private boolean confident;
  private boolean tired;
  private boolean accepting;
  private boolean accommodating;
  private boolean anxious;
  private boolean resigned;
  private boolean frustrated;
  private boolean angry;
  private boolean excited;
  private long date;
  private long reviewDate;


  public void setDecisionJournalId(String decisionJournalId) {
    this.decisionJournalId = decisionJournalId;
  }

  public void setDecisionJournalId(UUID decisionJournalId) {
    this.decisionJournalId = decisionJournalId.toString();
  }

  public void setWhatHappened(String whatHappened) {

  }
}
