package menstruapp.application.registermenstruation;

import menstruapp.application.exception.ValidationException;
import menstruapp.domain.menstruation.MenstruationRegistries;
import menstruapp.domain.menstruation.RegistryMenstruationType;
import menstruapp.infraestructure.internal.adapters.RegisterMenstruationPersistenceServiceImpl;
import menstruapp.infraestructure.internal.data.MenstruationRegistryEntity;
import menstruapp.infraestructure.internal.data.MenstruationRegistryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class RegisterMenstruationHandlerShould {

  RegisterMenstruationHandler handler;
  MenstruationRegistries menstruationRegistries;
  MenstruationRegistryRepository repositoryMock =
      Mockito.mock(MenstruationRegistryRepository.class);
  RegisterMenstruationPersistenceService persistenceService =
      new RegisterMenstruationPersistenceServiceImpl(repositoryMock);

  @BeforeEach
  void init() {
    // Only mock external elements to the system -> registries is not a mock
    menstruationRegistries = MenstruationRegistries.of();
    handler = new RegisterMenstruationHandler(menstruationRegistries, persistenceService);
  }

  @Test
  public void registerMenstruationEvent() throws ValidationException {
    RegisterMenstruationCommand command =
        new RegisterMenstruationCommand(
            UUID.randomUUID(), RegistryMenstruationType.BEGINNING, LocalDate.now());
    int registriesOriginalSize = menstruationRegistries.getRegistries().size();

    handler.handle(command);

    // Verify that a new registry is added
    assertEquals(menstruationRegistries.getRegistries().size(), registriesOriginalSize + 1);

    // Verify that the save method in the persistence service is called
    MenstruationRegistryEntity menstruationRegistryEntity =
        new MenstruationRegistryEntity(command.getUuid(), command.getType(), command.getDate());
    Mockito.verify(repositoryMock, times(1))
        .save(ArgumentMatchers.refEq(menstruationRegistryEntity));
  }

  @Test
  public void acceptOldRegistries() throws ValidationException {
    LocalDate today = LocalDate.now();
    LocalDate pastDate = today.minusDays(3);
    RegisterMenstruationCommand command =
        new RegisterMenstruationCommand(
            UUID.randomUUID(), RegistryMenstruationType.BEGINNING, pastDate);
    int registriesOriginalSize = menstruationRegistries.getRegistries().size();

    handler.handle(command);

    // Verify that a new registry is added
    assertEquals(menstruationRegistries.getRegistries().size(), registriesOriginalSize + 1);

    // Verify that the save method in the persistence service is called
    MenstruationRegistryEntity menstruationRegistryEntity =
        new MenstruationRegistryEntity(command.getUuid(), command.getType(), command.getDate());
    Mockito.verify(repositoryMock, times(1))
        .save(ArgumentMatchers.refEq(menstruationRegistryEntity));
  }

  @Test
  public void catchExistingRegistryException()
      throws ValidationException, MenstruationRegistries.InvalidRegistryDateException,
          MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryTypeException {

    RegisterMenstruationCommand command =
        new RegisterMenstruationCommand(
            UUID.randomUUID(), RegistryMenstruationType.BEGINNING, LocalDate.now());

    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now());
    int registriesOriginalSize = menstruationRegistries.getRegistries().size();
    handler.handle(command);

    assertEquals(menstruationRegistries.getRegistries().size(), registriesOriginalSize);
    MenstruationRegistryEntity menstruationRegistryEntity =
        new MenstruationRegistryEntity(command.getUuid(), command.getType(), command.getDate());
    Mockito.verify(repositoryMock, never())
        .save(ArgumentMatchers.refEq(menstruationRegistryEntity));
  }

  @Test
  public void throwValidationExceptionForFutureDateCommand() {

    LocalDate today = LocalDate.now();
    LocalDate futureDate = today.plusDays(3);
    RegisterMenstruationCommand command =
        new RegisterMenstruationCommand(
            UUID.randomUUID(), RegistryMenstruationType.BEGINNING, futureDate);

    ValidationException thrownException =
        assertThrows(ValidationException.class, () -> handler.handle(command));
    assertEquals(thrownException.getMessage(), "Registry date validation error.");
  }

  @Test
  public void throwValidationExceptionInvalidTypeCommand()
      throws MenstruationRegistries.InvalidRegistryDateException,
          MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryTypeException {

    RegisterMenstruationCommand command =
        new RegisterMenstruationCommand(
            UUID.randomUUID(), RegistryMenstruationType.BEGINNING, LocalDate.now());
    LocalDate today = LocalDate.now();
    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, today.minusDays(4));

    ValidationException thrownException =
        assertThrows(ValidationException.class, () -> handler.handle(command));
    assertEquals(thrownException.getMessage(), "Registry type validation error.");
  }
}
