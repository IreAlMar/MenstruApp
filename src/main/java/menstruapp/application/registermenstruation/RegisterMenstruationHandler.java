package menstruapp.application.registermenstruation;

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
  public Void handle(RegisterMenstruationCommand command) {
    try {
      MenstruationRegistry registry =
          menstruationRegistries.addRegistry(command.getType(), command.getDate());
      persistenceService.add(command.getUuid(), registry);

    } catch (MenstruationRegistries.ExistingRegistryException | MenstruationRegistries.InvalidRegistryDateException e) {
      e.printStackTrace();
    }

    return null;
  }
}
