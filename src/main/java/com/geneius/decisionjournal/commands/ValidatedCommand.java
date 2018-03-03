package com.geneius.decisionjournal.commands;

public interface ValidatedCommand {
  default boolean isValid() {
    return true;
  }
}
