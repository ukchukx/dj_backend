package com.geneius.decisionjournal.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, value = {"createdAt", "updatedAt"})
public class DecisionJournal {
  @Id
  private String id;
  private String index;
  private long date;
  private long reviewDate;
  private String decision;
  private String situation;
  private String problemStatement;
  private String variables;
  private String complications;
  private String alternatives;
  private String outcomeRange;
  private String expectations;
  private String outcome;
  private String whatHappened;
  private boolean energized;
  private boolean focused;
  private boolean relaxed;
  private boolean confident;
  private boolean tired;
  private boolean accepting;
  private boolean accomodating;
  private boolean anxious;
  private boolean resigned;
  private boolean frustrated;
  private boolean angry;
  private boolean high;
  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;
  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;
}
