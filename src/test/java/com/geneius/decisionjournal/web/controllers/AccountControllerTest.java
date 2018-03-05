package com.geneius.decisionjournal.web.controllers;


import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class AccountControllerTest extends BaseControllerTest {

  @Test
  public void shouldCreateUser() throws Exception {
    Map<String, String> map = getUserParams();

    mockMvc.perform(post("/accounts.signup")
        .content(toJson(map))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.name", is(map.get("name").toString())))
        .andExpect(jsonPath("$.data.token", is(not(empty()))))
        .andExpect(jsonPath("$.data.email", is(map.get("email").toString())));
  }

//  @Test
//  public void shouldReturnCurrentUser() throws Exception {
//    Map<String, Object> user = getUser();
//
//    mockMvc.perform(post("/accounts.currentUser")
//        .header("Authorization", String.format("Bearer %s", user.get("token").toString()))
//        .contentType(MediaType.APPLICATION_JSON)
//        .accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.data.name", is(user.get("name").toString())))
//        .andExpect(jsonPath("$.data.token", is(not(empty()))))
//        .andExpect(jsonPath("$.data.email", is(user.get("email").toString())));
//  }

  @Test
  public void shouldAuthenticateUser() throws Exception {
    Map<String, Object> user = getUser(),
        params = new HashMap(2);
    params.put("email", user.get("email").toString());
    params.put("password", "password");

     mockMvc.perform(post("/accounts.authenticate")
        .header("Authorization", String.format("Bearer %s", user.get("token").toString()))
        .content(toJson(params))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.token", is(not(empty()))))
        .andExpect(jsonPath("$.data.email", is(user.get("email").toString())));
  }

//  @Test
//  public void shouldUpdateUser() throws Exception {
//    Map<String, Object> user = getUser(),
//                        params = new HashMap(2);
//    params.put("name", "New Name");
//    params.put("id", user.get("id").toString());
//
//    mockMvc.perform(post("/accounts.updateUser")
//        .content(toJson(params))
//        .contentType(MediaType.APPLICATION_JSON)
//        .accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.data.name", is(user.get("name").toString())))
//        .andExpect(jsonPath("$.data.token", is(not(empty()))))
//        .andExpect(jsonPath("$.data.email", is(user.get("email").toString())));
//  }

  private Map<String, Object> getUser() throws Exception {
    String result = mockMvc.perform(post("/accounts.signup")
        .content(toJson(getUserParams()))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse()
        .getContentAsString();

    Map<String, Object> data = toMap(result);
    return (HashMap<String, Object>) data.get("data");
  }
}
