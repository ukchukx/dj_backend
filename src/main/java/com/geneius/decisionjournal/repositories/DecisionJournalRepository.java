package com.geneius.decisionjournal.repositories;

import com.geneius.decisionjournal.entities.DecisionJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DecisionJournalRepository extends JpaRepository<DecisionJournal, String> {
  List<DecisionJournal> findByAccountId(String accountId);

  Optional<DecisionJournal> findByIdAndIndex(String id, String index);
}
