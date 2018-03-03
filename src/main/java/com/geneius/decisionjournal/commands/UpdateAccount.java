package com.geneius.decisionjournal.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class UpdateAccount implements ValidatedCommand {
  @TargetAggregateIdentifier
  private String accountId;
  private String name;
  private String password;

  public void setPassword(String password) {
    this.password = new BCryptPasswordEncoder().encode(password);
  }

  @Override
  public boolean isValid() {
    return (name != null && !name.isEmpty()) || (password != null && !password.isEmpty());
  }
}
