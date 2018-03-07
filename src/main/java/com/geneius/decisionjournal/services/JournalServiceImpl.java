package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Journal;
import com.geneius.decisionjournal.repositories.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JournalServiceImpl implements JournalService {
  @Autowired
  private JournalRepository journalRepository;

  private static Logger logger = LoggerFactory.getLogger(JournalService.class);

  @Override
  public List<Journal> listDecisionJournals() {
    return journalRepository.findAll();
  }

  @Override
  public List<Journal> listByAccount(UUID id) {
    return journalRepository.findByAccountId(id);
  }

  @Override
  public Journal save(Journal journal) {
    return journalRepository.save(journal);
  }

  @Override
  public Optional<Journal> getById(UUID id) {
    return journalRepository.findById(id);
  }

  @Override
  public Optional<Journal> getByAccountIdAndIndex(UUID id, String index) {
    logger.warn("Id {}, Index {}", id, index);
    logger.warn("res {}", journalRepository.findByAccountIdAndIndex(id, index));
    return journalRepository.findByAccountIdAndIndex(id, index);
  }

  @Override
  public void deleteAll() {
    journalRepository.deleteAll();
  }
}
