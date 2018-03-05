package com.geneius.decisionjournal.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UpdateAccount implements ValidatedCommand {
  @TargetAggregateIdentifier
  private UUID accountId;
  private String name;
  private String password;
  private boolean disabled;

  public void setPassword(String password) {
    this.password = new BCryptPasswordEncoder().encode(password);
  }

  @Override
  public boolean isValid() {
    return (name != null && !name.isEmpty()) ||
        (password != null && !password.isEmpty()) ||
        disabled;
  }
}
