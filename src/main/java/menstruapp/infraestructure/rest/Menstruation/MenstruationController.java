package menstruapp.infraestructure.rest.Menstruation;

import menstruapp.application.exception.ValidationException;
import menstruapp.application.registermenstruation.RegisterMenstruationCommand;
import menstruapp.application.registermenstruation.RegisterMenstruationHandler;
import menstruapp.application.retrievementruations.FindMenstruationRegistriesHandler;
import menstruapp.application.retrievementruations.FindMenstruationRegistriesQuery;
import menstruapp.domain.menstruation.MenstruationRegistry;
import menstruapp.domain.menstruation.RegistryMenstruationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.TreeSet;
import java.util.UUID;

@RestController
@RequestMapping("/menstruation")
public class MenstruationController {
    public static String MENSTRUATION_REGISTRY_URL = "menstruation";
    @Autowired
    RegisterMenstruationHandler handler;

    @Autowired
    FindMenstruationRegistriesHandler findMenstruationRegistriesHandler;

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

    @GetMapping
    public ResponseEntity getMenstruationRegistries() {
        FindMenstruationRegistriesQuery query = new FindMenstruationRegistriesQuery();
        TreeSet<MenstruationRegistry> registries;
        try {
            registries = findMenstruationRegistriesHandler.handle(query);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(registries, HttpStatus.OK);
    }
}
