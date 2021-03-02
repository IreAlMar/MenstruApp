package menstruapp.infraestructure.rest;

import menstruapp.application.ValidationException;
import menstruapp.application.registermenstruation.RegisterMenstruationCommand;
import menstruapp.application.registermenstruation.RegisterMenstruationHandler;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static menstruapp.infraestructure.rest.Menstruation.MenstruationController.MENSTRUATION_REGISTRY_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenstruationControllerShould {

  @Autowired private MockMvc mockMvc;
  @MockBean private RegisterMenstruationHandler registerMenstruationHandlerMock;
  PodamFactory factory = new PodamFactoryImpl();
  RegisterMenstruationCommand registerMenstruationCommand =
      factory.manufacturePojo(RegisterMenstruationCommand.class);
  String content =
      "{\"uuid\": \""
          + registerMenstruationCommand.getUuid()
          + "\", \"type\": \""
          + registerMenstruationCommand.getType()
          + "\", \"date\": \""
          + registerMenstruationCommand.getDate()
          + "\"}";

  //TODO Handler is already tested, mock the handler and verify that the handler is called and
  // the command is correctly sent, what arrives as json is correctly transformed in a command
  // object
  @Test
  public void receiveDataToSaveAMenstruationRegistry() throws Exception, ValidationException {
    Mockito.doNothing().when(registerMenstruationHandlerMock).handle(any());

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/" + MENSTRUATION_REGISTRY_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    ArgumentCaptor<RegisterMenstruationCommand> savedCaptor = ArgumentCaptor.forClass(RegisterMenstruationCommand.class);
    Mockito.verify(registerMenstruationHandlerMock).handle(savedCaptor.capture());
    RegisterMenstruationCommand registerMenstruationCommandCaptor = savedCaptor.getValue();
    assertEquals(registerMenstruationCommandCaptor.getType(), registerMenstruationCommand.getType());
    assertEquals(registerMenstruationCommandCaptor.getDate(), registerMenstruationCommand.getDate());
    assertEquals(registerMenstruationCommandCaptor.getUuid(), registerMenstruationCommand.getUuid());
  }

  @Test
  public void throwErrorMessageGivenInvalidParameters() throws Exception, ValidationException {
    Mockito.when(registerMenstruationHandlerMock.handle(any())).thenThrow(ValidationException.of());
    mockMvc
            .perform(
                    MockMvcRequestBuilders.post("/" + MENSTRUATION_REGISTRY_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content)
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
    .andExpect(content().string(equalTo("Validation error.")));
  }
}
