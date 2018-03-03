package com.geneius.decisionjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DecisionjournalApplication {

	public static void main(String[] args) {
   SpringApplication.run(DecisionjournalApplication.class, args);
	}
}
