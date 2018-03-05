package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.DecisionJournal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DecisionJournalService {
  List<DecisionJournal> listDecisionJournals();
  List<DecisionJournal> listByAccount(UUID id);
  DecisionJournal save(DecisionJournal decisionJournal);
  Optional<DecisionJournal> getById(UUID id);
  Optional<DecisionJournal> getByIdAndIndex(UUID id, String index);
  void deleteAll();
}
