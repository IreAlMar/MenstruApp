package menstruapp.infraestructure.rest;

import menstruapp.application.MaxAndMin;
import menstruapp.application.MetricSpecifics;
import menstruapp.application.SaveMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class SaveMetricController {

      @Autowired
      private SaveMetricService service;

  @PostMapping
  public ResponseEntity saveNewMetric(@RequestBody RequestDTO requestDTO) {
    // TODO me he quedado aquí, add tests and control failure cases
    MetricSpecifics metricSpecifics = service.saveMetric(requestDTO.id, requestDTO.description,
            requestDTO.min, requestDTO.max);
    return new ResponseEntity(new ResponseDTO(metricSpecifics), HttpStatus.OK);
  }

  private class ResponseDTO {
    public String id;
    public String description;
    public MaxAndMin maxAndMin;

    public ResponseDTO(MetricSpecifics metricSpecifics) {
      this.id = metricSpecifics.getId();
      this.description = metricSpecifics.getDescription();
      this.maxAndMin = metricSpecifics.getMaxAndMin();
    }
  }
}
