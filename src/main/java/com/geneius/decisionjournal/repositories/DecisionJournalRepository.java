package com.geneius.decisionjournal.repositories;

import com.geneius.decisionjournal.entities.DecisionJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DecisionJournalRepository extends JpaRepository<DecisionJournal, UUID> {
  List<DecisionJournal> findByAccountId(UUID accountId);

  Optional<DecisionJournal> findByIdAndIndex(UUID id, String index);
}
