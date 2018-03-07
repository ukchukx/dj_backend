package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Journal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DecisionJournalService {
  List<Journal> listDecisionJournals();
  List<Journal> listByAccount(UUID id);
  Journal save(Journal journal);
  Optional<Journal> getById(UUID id);
  Optional<Journal> getByAccountIdAndIndex(UUID id, String index);
  void deleteAll();
}
