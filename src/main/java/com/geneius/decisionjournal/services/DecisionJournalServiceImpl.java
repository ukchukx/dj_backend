package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Journal;
import com.geneius.decisionjournal.repositories.DecisionJournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DecisionJournalServiceImpl implements DecisionJournalService {
  @Autowired
  private DecisionJournalRepository decisionJournalRepository;

  private static Logger logger = LoggerFactory.getLogger(DecisionJournalService.class);

  @Override
  public List<Journal> listDecisionJournals() {
    return decisionJournalRepository.findAll();
  }

  @Override
  public List<Journal> listByAccount(UUID id) {
    logger.warn("All journals: {}", listDecisionJournals());
    return decisionJournalRepository.findByAccountId(id);
  }

  @Override
  public Journal save(Journal journal) {
    return decisionJournalRepository.save(journal);
  }

  @Override
  public Optional<Journal> getById(UUID id) {
    return decisionJournalRepository.findById(id);
  }

  @Override
  public Optional<Journal> getByIdAndIndex(UUID id, String index) {
    return decisionJournalRepository.findByIdAndIndex(id, index);
  }

  @Override
  public void deleteAll() {
    decisionJournalRepository.deleteAll();
  }
}
