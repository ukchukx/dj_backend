package com.geneius.decisionjournal.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geneius.decisionjournal.DecisionjournalApplication;
import com.geneius.decisionjournal.services.AccountService;
import com.geneius.decisionjournal.services.DecisionJournalService;
import com.geneius.decisionjournal.web.security.WebSecurity;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BaseControllerTest {
  @Autowired protected WebApplicationContext context;
  @Autowired protected AccountService accountService;
  @Autowired protected DecisionJournalService decisionJournalService;

  protected MockMvc mockMvc;

  protected static Logger logger = LoggerFactory.getLogger(BaseControllerTest.class);

  @Before
  public void initTests() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @After
  public void tearDown() {
    accountService.deleteAll();
    decisionJournalService.deleteAll();
  }

  protected byte[] toJson(Object obj) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(obj).getBytes();
  }

  protected Map<String, Object> toMap(String string) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};

    return mapper.readValue(new ByteArrayInputStream(string.getBytes("UTF-8")), typeRef);
  }

  protected Map<String, String> getUserParams() {
    Map<String, String> map = new HashMap(3);
    map.put("email", "test@user.com");
    map.put("name", "Temp User");
    map.put("password", "password");
    return map;
  }
}
