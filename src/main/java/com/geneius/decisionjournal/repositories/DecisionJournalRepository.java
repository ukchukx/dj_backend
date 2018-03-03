package com.geneius.decisionjournal.repositories;

import com.geneius.decisionjournal.entities.DecisionJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecisionJournalRepository extends JpaRepository<DecisionJournal, String> {
}
