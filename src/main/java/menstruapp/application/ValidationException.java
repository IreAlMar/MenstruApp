package menstruapp.application;

import menstruapp.domain.menstruation.MenstruationRegistries;

public class ValidationException extends Throwable {
// TODO: arrastrar los mensajes desde los niveles más inferiores que den información: fecha no
//  válida por x razón
  private ValidationException(String message) {
    super(message);
  }

  public static ValidationException of(MenstruationRegistries.InvalidRegistryDateException e) {
    return new ValidationException("Registry date validation error.");
  }

  public static ValidationException of(MenstruationRegistries.InvalidRegistryTypeException e) {
    return new ValidationException("Registry type validation error.");
  }

  public static ValidationException of() {
    return new ValidationException("Validation error.");
  }
}
