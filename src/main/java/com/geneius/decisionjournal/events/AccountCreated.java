package com.geneius.decisionjournal.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCreated {
  private String accountId;
  private String email;
  private String name;
  private String password;
}
