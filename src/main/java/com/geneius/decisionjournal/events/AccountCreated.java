package com.geneius.decisionjournal.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AccountCreated {
  private UUID accountId;
  private String email;
  private String name;
  private String password;
  private boolean enabled;
}
