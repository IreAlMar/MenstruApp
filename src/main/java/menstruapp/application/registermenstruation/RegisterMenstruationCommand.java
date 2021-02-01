package menstruapp.application.registermenstruation;

import menstruapp.domain.menstruation.RegistryMenstruationType;

import java.time.LocalDate;
import java.util.UUID;

public class RegisterMenstruationCommand {
  private final UUID uuid;
  private final RegistryMenstruationType type;
  private final LocalDate date;

  public RegisterMenstruationCommand(UUID uuid, RegistryMenstruationType type, LocalDate date) {
    this.uuid = uuid;
    this.type = type;
    this.date = date;
  }

  public UUID getUuid() {
    return uuid;
  }

  public RegistryMenstruationType getType() {
    return type;
  }

  public LocalDate getDate() {
    return date;
  }
}
