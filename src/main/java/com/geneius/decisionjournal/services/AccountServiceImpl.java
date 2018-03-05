package com.geneius.decisionjournal.services;

import com.geneius.decisionjournal.entities.Account;
import com.geneius.decisionjournal.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public List<Account> listAccounts() {
    return accountRepository.findAll();
  }

  @Override
  public Account save(Account account) {
    return accountRepository.save(account);
  }

  @Override
  public Optional<Account> findByEmail(String email) {
    return accountRepository.findByEmail(email);
  }

  @Override
  public Optional<Account> getById(UUID id) {
    return accountRepository.findById(id);
  }

  @Override
  public void deleteAll() {
    accountRepository.deleteAll();
  }

  @Override
  public void login(Account user) {
    Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(),
        user.getPassword(), user.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    Optional<Account> optional = findByEmail(s);
    if (!optional.isPresent()) throw new UsernameNotFoundException(s);
    return  optional.get();
  }

  @Override
  public Account getAccountFromPrincipal(Principal principal) {
    return (Account) loadUserByUsername(principal.getName());
  }
}
