package com.ufg.inf.ps.selfservice.application.authenticate;

import com.ufg.inf.ps.selfservice.application.authenticate.command.LoginCommand;
import com.ufg.inf.ps.selfservice.core.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jonathas.assuncao on 10/12/2020
 * @project SelfService
 */
class AuthenticationResourceITest extends IntegrationTest {

  @Test
  void authenticate_client() throws Exception {
    final String username = "cliente@email.com";
    final String password = "@email.com";
    LoginCommand command = new LoginCommand(username, password);

    ResultActions actions = mockMvc().perform(post("/api/authentication")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isOk());

    actions.andExpect(jsonPath("$.token").isNotEmpty());
  }

  @Test
  void authenticate_supplier() throws Exception {
    final String username = "fornecedor@email.com";
    final String password = "@email.com";
    LoginCommand command = new LoginCommand(username, password);

    ResultActions actions = mockMvc().perform(post("/api/authentication")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isOk());

    actions.andExpect(jsonPath("$.token").isNotEmpty());
  }

  @Test
  void authenticate_wrongUsername() throws Exception {
    final String username = "naoexiste@email.com";
    final String password = "@email.com";
    LoginCommand command = new LoginCommand(username, password);

    ResultActions actions = mockMvc().perform(post("/api/authentication")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isBadRequest());

    actions.andExpect(jsonPath("$.code").value("error.invalidParam"));
    actions.andExpect(jsonPath("$.message").value("As credenciais informadas estão erradas"));
  }

  @Test
  void authenticate_wrongPassword() throws Exception {
    final String username = "fornecedor@email.com";
    final String password = "senhaErrada";
    LoginCommand command = new LoginCommand(username, password);

    ResultActions actions = mockMvc().perform(post("/api/authentication")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json(command)))
        .andExpect(status().isBadRequest());

    actions.andExpect(jsonPath("$.code").value("error.invalidParam"));
    actions.andExpect(jsonPath("$.message").value("As credenciais informadas estão erradas"));
  }
}