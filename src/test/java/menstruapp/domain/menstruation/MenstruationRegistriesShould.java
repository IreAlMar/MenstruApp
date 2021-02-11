package menstruapp.domain.menstruation;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenstruationRegistriesShould {
  @Test
  public void addARegistries()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException,
          MenstruationRegistries.InvalidRegistryTypeException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    int originalSize = menstruationRegistries.getRegistries().size();

    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now());
    assertEquals(menstruationRegistries.getRegistries().size(), originalSize + 1);
  }

  @Test
  public void acceptOldRegistries()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException,
          MenstruationRegistries.InvalidRegistryTypeException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    LocalDate today = LocalDate.now();
    LocalDate pastDate = today.minusDays(3);
    int originalSize = menstruationRegistries.getRegistries().size();

    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, pastDate);
    assertEquals(menstruationRegistries.getRegistries().size(), originalSize + 1);
  }

  @Test
  public void notAddDuplicatedRegistries()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException,
          MenstruationRegistries.InvalidRegistryTypeException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now());
    int originalSize = menstruationRegistries.getRegistries().size();

    assertThrows(
        MenstruationRegistries.ExistingRegistryException.class,
        () -> menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, LocalDate.now()));
    assertEquals(menstruationRegistries.getRegistries().size(), originalSize);
  }

  @Test
  public void notAddRegistriesWithFutureDates() {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    LocalDate today = LocalDate.now();
    LocalDate futureDate = today.plusDays(3);
    int originalSize = menstruationRegistries.getRegistries().size();

    assertThrows(
        MenstruationRegistries.InvalidRegistryDateException.class,
        () -> menstruationRegistries.addRegistry(RegistryMenstruationType.END, futureDate));
    assertEquals(menstruationRegistries.getRegistries().size(), originalSize);
  }

  @Test
  public void keepCameWentFlow()
      throws MenstruationRegistries.ExistingRegistryException,
          MenstruationRegistries.InvalidRegistryDateException,
          MenstruationRegistries.InvalidRegistryTypeException {
    MenstruationRegistries menstruationRegistries = MenstruationRegistries.of();
    LocalDate today = LocalDate.now();
    menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, today.minusDays(4));
    int originalSize = menstruationRegistries.getRegistries().size();

    assertThrows(
        MenstruationRegistries.InvalidRegistryTypeException.class,
        () -> menstruationRegistries.addRegistry(RegistryMenstruationType.BEGINNING, today));
    menstruationRegistries.addRegistry(RegistryMenstruationType.END, today);
    assertEquals(menstruationRegistries.getRegistries().size(), originalSize + 1);
  }
}
