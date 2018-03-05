package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.DecisionJournal;
import com.geneius.decisionjournal.repositories.DecisionJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DecisionJournalServiceImpl implements DecisionJournalService {
  @Autowired
  private DecisionJournalRepository decisionJournalRepository;

  @Override
  public List<DecisionJournal> listDecisionJournals() {
    return decisionJournalRepository.findAll();
  }

  @Override
  public List<DecisionJournal> listByAccount(UUID id) {
    return decisionJournalRepository.findByAccountId(id);
  }

  @Override
  public DecisionJournal save(DecisionJournal decisionJournal) {
    return decisionJournalRepository.save(decisionJournal);
  }

  @Override
  public Optional<DecisionJournal> getById(UUID id) {
    return decisionJournalRepository.findById(id);
  }

  @Override
  public Optional<DecisionJournal> getByIdAndIndex(UUID id, String index) {
    return decisionJournalRepository.findByIdAndIndex(id, index);
  }

  @Override
  public void deleteAll() {
    decisionJournalRepository.deleteAll();
  }
}
