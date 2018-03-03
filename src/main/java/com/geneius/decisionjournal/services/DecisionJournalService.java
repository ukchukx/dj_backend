package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.DecisionJournal;

import java.util.List;
import java.util.Optional;

public interface DecisionJournalService {
  List<DecisionJournal> listDecisionJournals();
  List<DecisionJournal> listByAccount(String id);
  DecisionJournal save(DecisionJournal decisionJournal);
  Optional<DecisionJournal> getById(String id);
  Optional<DecisionJournal> getByIdAndIndex(String id, String index);
}
