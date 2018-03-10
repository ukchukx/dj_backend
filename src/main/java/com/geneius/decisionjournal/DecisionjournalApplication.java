package com.geneius.decisionjournal;

import org.axonframework.config.EventHandlingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class DecisionjournalApplication {

	public static void main(String[] args) {
   SpringApplication.run(DecisionjournalApplication.class, args);
	}

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Autowired
  // public void configure(EventHandlingConfiguration config) {
  //  config.usingTrackingProcessors();
  // }
}
