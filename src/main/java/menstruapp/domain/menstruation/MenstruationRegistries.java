package menstruapp.domain.menstruation;

import java.time.LocalDate;
import java.util.Objects;
import java.util.TreeSet;

public class MenstruationRegistries {
  private final TreeSet<MenstruationRegistry> registries;

  private MenstruationRegistries(TreeSet<MenstruationRegistry> registries) {
    Objects.requireNonNull(registries);
    this.registries = registries;
  }

  public static MenstruationRegistries of() {
    return new MenstruationRegistries(new TreeSet<>());
  }

  public static MenstruationRegistries of(TreeSet<MenstruationRegistry> registries) {
    return new MenstruationRegistries(registries);
  }

  public MenstruationRegistry addRegistry(RegistryMenstruationType type, LocalDate date)
      throws ExistingRegistryException, InvalidRegistryDateException, InvalidRegistryTypeException {
    MenstruationRegistry registry = MenstruationRegistry.of(type, date);

    //In the future, call here the service that provides the data from the database

    // No future dates are added
    if (date.isAfter(LocalDate.now())) {
      throw new InvalidRegistryDateException();
    }

    // No same registry
    if (registries.contains(registry)) {
      throw new ExistingRegistryException();
    }

    // Keep came went flow
    if (!registries.isEmpty() && registries.last().getType() == type) {
      throw new InvalidRegistryTypeException();
    }

    registries.add(registry);

    return registry;
  }

  public TreeSet<MenstruationRegistry> getRegistries() {
    return registries;
  }

  public class ExistingRegistryException extends Throwable {}

  public class InvalidRegistryDateException extends Throwable {}

  public class InvalidRegistryTypeException extends Throwable {}
}
