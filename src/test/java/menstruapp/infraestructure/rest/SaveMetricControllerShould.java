package menstruapp.infraestructure.rest;

import menstruapp.application.SaveMetricService;
import menstruapp.domain.metric.InvalidMetricValueException;
import menstruapp.infraestructure.internal.adapters.MetricSpecificsImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SaveMetricControllerShould {

  public static final String METRICS_URL = "/metrics";
  @Autowired private MockMvc mvc;
  @MockBean private SaveMetricService service;
  PodamFactory factory = new PodamFactoryImpl();
  MetricSpecificsImpl metricSpecifics = factory.manufacturePojo(MetricSpecificsImpl.class);
  String content =
      "{\"id\": \""
          + metricSpecifics.getId()
          + "\", \"description\": \""
          + metricSpecifics.getDescription()
          + "\", \"min\":0"
          + ", \"max\":10}";

  @Test
  public void saveMetric() throws Exception, InvalidMetricValueException {
    Mockito.when(service.saveMetric(anyString(), anyString(), anyInt(), anyInt()))
        .thenReturn(metricSpecifics);
    // TODO maxAndMin is null because of Podam -> MaxAndMinImpl in MetricSpecificsImpl

    mvc.perform(
            MockMvcRequestBuilders.post(METRICS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", Matchers.equalTo(metricSpecifics.getId())))
        .andExpect(jsonPath("$.description", Matchers.equalTo(metricSpecifics.getDescription())))
        .andExpect(
            jsonPath("$.maxAndMin.min", Matchers.equalTo(metricSpecifics.getMaxAndMin().getMin())))
        .andExpect(
            jsonPath("$.maxAndMin.max", Matchers.equalTo(metricSpecifics.getMaxAndMin().getMax())));
  }

  @Test
  public void throwErrorMessageGivenInvalidParameters()
      throws Exception, InvalidMetricValueException {

    Mockito.when(service.saveMetric(anyString(), anyString(), anyInt(), anyInt()))
        .thenThrow(new IllegalArgumentException());

    mvc.perform(
            MockMvcRequestBuilders.post(METRICS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(equalTo("Invalid parameter")));
  }
}
