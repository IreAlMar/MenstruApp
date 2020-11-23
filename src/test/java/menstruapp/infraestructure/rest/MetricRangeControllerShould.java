package menstruapp.infraestructure.rest;

import menstruapp.application.MetricRangeService;
import menstruapp.application.exception.InvalidIdException;
import menstruapp.domain.metric.Metric;
import menstruapp.infraestructure.internal.adapters.MaxAndMinImpl;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MetricRangeControllerShould {

  public static final String METRIC_RANGE_URL = "/range";
  PodamFactory factory = new PodamFactoryImpl();
  Metric metric = factory.manufacturePojo(Metric.class);
  MaxAndMinImpl maxAndMin =
      new MaxAndMinImpl(metric.getRange().getMin(), metric.getRange().getMax());

  @Autowired private MockMvc mvc;

  @MockBean private MetricRangeService metricRangeService;

  @Test
  public void getRangeGivenAnId() throws Exception, InvalidIdException {

    Mockito.when(metricRangeService.getMetricRange(anyString())).thenReturn(maxAndMin);

    mvc.perform(
            MockMvcRequestBuilders.get(METRIC_RANGE_URL + "?id=" + metric.getId().getId())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.min", Matchers.equalTo(metric.getRange().getMin())))
        .andExpect(jsonPath("$.max", Matchers.equalTo(metric.getRange().getMax())));
  }

  @Test
  public void throwErrorMessageGivenAnInvalidId() throws InvalidIdException, Exception {
    Mockito.when(metricRangeService.getMetricRange(anyString())).thenThrow(new InvalidIdException());

    mvc.perform(
            MockMvcRequestBuilders.get(METRIC_RANGE_URL + "?id=" + metric.getId().getId())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(equalTo("Invalid id " + metric.getId().getId())));
  }
}
