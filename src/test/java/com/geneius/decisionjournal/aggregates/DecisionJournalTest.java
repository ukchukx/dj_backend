package com.geneius.decisionjournal.aggregates;

import com.geneius.decisionjournal.commands.CreateDecisionJournal;
import com.geneius.decisionjournal.commands.UpdateDecisionJournal;
import com.geneius.decisionjournal.events.DecisionJournalCreated;
import com.geneius.decisionjournal.events.DecisionJournalWhatHappenedChanged;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class DecisionJournalTest {
  private FixtureConfiguration<DecisionJournal> fixture;

  private CreateDecisionJournal buildCreateCommand() {
    CreateDecisionJournal create = new CreateDecisionJournal();
    create.setDecisionJournalId(UUID.randomUUID());
    create.setAccountId(UUID.randomUUID().toString());
    create.setIndex("1");
    create.setDate(new Date().getTime());
    create.setReviewDate(new Date().getTime());
    create.setDecision("Run the test");
    create.setSituation("Writing tests");
    create.setProblemStatement("Code is too verbose");
    create.setVariables("Undefined and unknown");
    create.setComplications("There are obvious complications");
    create.setAlternatives("Not sure if there are any");
    create.setOutcomeRange("Not wide enough");
    create.setExpectations("Expect the code to write itself");
    create.setOutcome("All tests should hopefully pass");
    create.setTired(true);

    return create;
  }

  private DecisionJournalCreated buildCreatedEvent(CreateDecisionJournal create) {
    DecisionJournalCreated created = new DecisionJournalCreated();
    created.setDecisionJournalId(create.getDecisionJournalId());
    created.setAccountId(create.getAccountId());
    created.setIndex(create.getIndex());
    created.setDate(create.getDate());
    created.setReviewDate(create.getReviewDate());
    created.setDecision(create.getDecision());
    created.setSituation(create.getSituation());
    created.setProblemStatement(create.getProblemStatement());
    created.setVariables(create.getVariables());
    created.setComplications(create.getComplications());
    created.setAlternatives(create.getAlternatives());
    created.setOutcomeRange(create.getOutcomeRange());
    created.setExpectations(create.getExpectations());
    created.setOutcome(create.getOutcome());
    created.setWhatHappened(create.getWhatHappened());
    created.setEnergized(create.isEnergized());
    created.setFocused(create.isFocused());
    created.setRelaxed(create.isRelaxed());
    created.setConfident(create.isConfident());
    created.setTired(create.isTired());
    created.setAccepting(create.isAccepting());
    created.setAccomodating(create.isAccommodating());
    created.setAnxious(create.isAnxious());
    created.setResigned(create.isResigned());
    created.setFrustrated(create.isFrustrated());
    created.setAngry(create.isAngry());
    created.setExcited(create.isExcited());

    return created;
  }

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture(DecisionJournal.class);
  }

  @Test
  public void createDecisionJournalShouldReturnDecisionJournalCreated() throws Exception {
    CreateDecisionJournal createCommand = buildCreateCommand();
    DecisionJournalCreated createdEvent = buildCreatedEvent(createCommand);

    fixture.givenNoPriorActivity()
        .when(createCommand)
        .expectEvents(createdEvent);
  }

  @Test
  public void updateDecisionJournalShouldSucceed() throws Exception {
    DecisionJournalCreated createdEvent = buildCreatedEvent(buildCreateCommand());
    UpdateDecisionJournal updateCommand = new UpdateDecisionJournal();
    updateCommand.setDecisionJournalId(createdEvent.getDecisionJournalId());
    updateCommand.setWhatHappened("Finished typing this command");

    DecisionJournalWhatHappenedChanged changed = new DecisionJournalWhatHappenedChanged();
    changed.setDecisionJournalId(updateCommand.getDecisionJournalId());
    changed.setWhatHappened(updateCommand.getWhatHappened());

    fixture.given(createdEvent)
        .when(updateCommand)
        .expectEvents(changed);
  }
}
