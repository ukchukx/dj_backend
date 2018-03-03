package com.geneius.decisionjournal.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountNameChanged {
  private String accountId;
  private String name;
}
