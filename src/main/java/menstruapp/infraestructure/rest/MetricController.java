package menstruapp.infraestructure.rest;

import menstruapp.application.MetricService;
import menstruapp.domain.metric.MetricRange;
import menstruapp.domain.shared.UniqueIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MetricController {

  @Autowired private MetricService metricService;

  @GetMapping(value = "/range", params = "id")
  public ResponseEntity getMetricRange(@RequestParam(value = "id") UUID id) {
    MetricRange metricRange = metricService.getMetricRange(UniqueIdentifier.of(id));
    return new ResponseEntity(metricRange, HttpStatus.OK);
  }
}
