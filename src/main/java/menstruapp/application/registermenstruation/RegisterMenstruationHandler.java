package menstruapp.application.registermenstruation;

import menstruapp.application.ValidationException;
import menstruapp.domain.framework.Handler;
import menstruapp.domain.menstruation.MenstruationRegistries;
import menstruapp.domain.menstruation.MenstruationRegistry;
import org.springframework.stereotype.Component;

@Component
public class RegisterMenstruationHandler implements Handler<Void, RegisterMenstruationCommand> {
  private final MenstruationRegistries menstruationRegistries;
  private final RegisterMenstruationPersistenceService persistenceService;

  public RegisterMenstruationHandler(
      MenstruationRegistries menstruationRegistries,
      RegisterMenstruationPersistenceService persistenceService) {
    this.menstruationRegistries = menstruationRegistries;
    this.persistenceService = persistenceService;
  }

  @Override
  public Void handle(RegisterMenstruationCommand command) throws ValidationException {
    try {
      MenstruationRegistry registry =
          menstruationRegistries.addRegistry(command.getType(), command.getDate());
      persistenceService.add(command.getUuid(), registry);

    } catch (MenstruationRegistries.ExistingRegistryException e) {
      // TODO: Log a warning message and test it is logged
    } catch (MenstruationRegistries.InvalidRegistryDateException e) {
      throw ValidationException.of(e);
    } catch (MenstruationRegistries.InvalidRegistryTypeException e) {
      throw ValidationException.of(e);
    }

    return null;
  }
}
