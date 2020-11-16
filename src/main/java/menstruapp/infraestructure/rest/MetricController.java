package menstruapp.infraestructure.rest;

import menstruapp.application.MaxAndMin;
import menstruapp.application.MetricService;
import menstruapp.application.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricController {

  @Autowired private MetricService metricService;

  @GetMapping(value = "/range", params = "id")
  public ResponseEntity getMetricRange(@RequestParam(value = "id") String id) {
    try {
      MaxAndMin metricRange = metricService.getMetricRange(id);
      return new ResponseEntity(new ResponseDTO(metricRange), HttpStatus.OK);
    } catch (InvalidIdException e) {
      return new ResponseEntity<>("Invalid id " + id, HttpStatus.BAD_REQUEST);
    }
  }

  private class ResponseDTO {
    public Integer min;
    public Integer max;

    public ResponseDTO(MaxAndMin maxAndMin) {
      this.min = maxAndMin.getMin();
      this.max = maxAndMin.getMax();
    }
  }
}
