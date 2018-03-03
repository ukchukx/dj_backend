package com.geneius.decisionjournal.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateAccount implements ValidatedCommand {
  private String accountId;
  private String email;
  private String password;
  private String name;


  public void setAccountId(String accountId) {
    this.accountId = accountId.trim();
  }

  public void setAccountId(UUID uuid) {
    this.accountId = uuid.toString();
  }

  public void setEmail(String email) {
    this.email = email.trim();
  }

  public void setPassword(String password) {
    this.password = new BCryptPasswordEncoder().encode(password);
  }

  public void setName(String name) {
    this.name = name.trim();
  }

  @Override
  public boolean isValid() {
    return accountId != null && !accountId.isEmpty() &&
        email != null && !email.isEmpty() &&
        password != null && !password.isEmpty() &&
        name != null && !name.isEmpty();
  }
}
