package com.geneius.decisionjournal.repositories;

import com.geneius.decisionjournal.entities.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DecisionJournalRepository extends JpaRepository<Journal, UUID> {
  List<Journal> findByAccountId(UUID accountId);

  Optional<Journal> findByAccountIdAndIndex(UUID id, String index);
}
