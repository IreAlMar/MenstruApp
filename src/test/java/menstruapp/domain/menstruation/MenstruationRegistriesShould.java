package menstruapp.domain.menstruation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MenstruationRegistriesShould {
  @Test
  public void addARegistries()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now());
  }

  @Test
  public void acceptOldRegistries()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    LocalDate today = LocalDate.now();
    LocalDate pastDate = today.minusDays(3);
    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, pastDate);
  }

  @Test
  public void notAddDuplicatedRegistries()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now());

    assertThrows(
        MenstruationRegistries.ExistingRegistryException.class,
        () -> {
          menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now());
        });
  }

  @Test
  public void notAddRegistriesWithFutureDates() {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    LocalDate today = LocalDate.now();
    LocalDate futureDate = today.plusDays(3);

    assertThrows(
        MenstruationRegistries.InvalidRegistryDateException.class,
        () -> {
          menstruationRegistries.addRegistry(RegistryMenstruationType.END, futureDate);
        });
  }

//  @Test
//  public void keepCameWentFlow()
//      throws MenstruationRegistries.ExistingRegistryException,
//          MenstruationRegistries.InvalidRegistryDateException {
//    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
//    LocalDate today = LocalDate.now();
//    menstruationRegistries.addRegistry(RegisterMenstruationType.BEGINNING, today.minusDays(4));
//    menstruationRegistries.addRegistry(RegisterMenstruationType.BEGINNING, today.minusDays(3));
//
//    assertThrows(
//            MenstruationRegistries.InvalidRegistryTypeException.class,
//        () -> {
//          menstruationRegistries.addRegistry(RegisterMenstruationType.BEGINNING, today);
//        });
//  }
}
