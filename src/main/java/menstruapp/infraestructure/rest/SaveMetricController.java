package menstruapp.infraestructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class SaveMetricController {

  //    @Autowired
  //    private SaveMetricService service;

  @PostMapping
  public ResponseEntity saveNewMetric(@RequestBody String text) {
    return ResponseEntity.ok(text);
  }
}
