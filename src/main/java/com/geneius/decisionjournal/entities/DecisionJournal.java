package com.geneius.decisionjournal.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "decision_journals")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class DecisionJournal {
  @Id
  @Type(type="pg-uuid")
  private UUID id;
  private UUID accountId;
  private String index;
  private long date;
  private long reviewDate;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String decision;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String situation;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String problemStatement;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String variables;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String complications;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String alternatives;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String outcomeRange;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String expectations;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String outcome;
  @Column(columnDefinition = "TEXT", nullable = false)
  private String whatHappened;
  private boolean energized;
  private boolean focused;
  private boolean relaxed;
  private boolean confident;
  private boolean tired;
  private boolean accepting;
  private boolean accommodating;
  private boolean anxious;
  private boolean resigned;
  private boolean frustrated;
  private boolean angry;
  private boolean excited;
  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;
}
