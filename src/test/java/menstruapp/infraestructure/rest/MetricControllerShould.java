package menstruapp.infraestructure.rest;

import menstruapp.application.MetricService;
import menstruapp.domain.metric.Metric;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MetricControllerShould {

  public static final String METRIC_RANGE_URL = "/range";
  PodamFactory factory = new PodamFactoryImpl();
  Metric metric = factory.manufacturePojo(Metric.class);

  @Autowired private MockMvc mvc;

  @MockBean private MetricService metricService;

  @Test
  public void getRangeGivenAnId() throws Exception {
    Mockito.when(metricService.getMetricRange(metric.getId())).thenReturn(metric.getRange());

    mvc.perform(
            MockMvcRequestBuilders.get(METRIC_RANGE_URL + "?id=" + metric.getId().getId())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    //            .andExpect(content());
  }
}
