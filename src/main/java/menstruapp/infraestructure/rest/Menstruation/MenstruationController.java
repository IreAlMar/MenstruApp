package menstruapp.infraestructure.rest.Menstruation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menstruation")
public class MenstruationController {
  public static String MENSTRUATION_REGISTRY_URL = "menstruation";

  @PostMapping
  public ResponseEntity saveNewMenstruationRegistry(@RequestBody RequestDTO requestDTO) {
    return new ResponseEntity(HttpStatus.CREATED);
  }
}
