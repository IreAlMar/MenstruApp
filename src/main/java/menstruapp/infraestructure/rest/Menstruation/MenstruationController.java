package menstruapp.infraestructure.rest.Menstruation;

import menstruapp.application.ValidationException;
import menstruapp.application.registermenstruation.RegisterMenstruationCommand;
import menstruapp.application.registermenstruation.RegisterMenstruationHandler;
import menstruapp.domain.menstruation.RegistryMenstruationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/menstruation")
public class MenstruationController {
  public static String MENSTRUATION_REGISTRY_URL = "menstruation";
  @Autowired
  RegisterMenstruationHandler handler;

  @PostMapping
  public ResponseEntity saveNewMenstruationRegistry(@RequestBody RequestDTO requestDTO) {
    RegisterMenstruationCommand command =
        new RegisterMenstruationCommand(UUID.fromString(requestDTO.uuid),
                RegistryMenstruationType.valueOf(requestDTO.type),
                LocalDate.parse(requestDTO.date));
    try {
      handler.handle(command);
    } catch (ValidationException e) {
      return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity(HttpStatus.CREATED);
  }
}
