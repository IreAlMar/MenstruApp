package menstruapp.domain.menstruation;

import java.time.LocalDate;
import java.util.*;

public class MenstruationRegistries {
  private final Set<MenstruationRegistry> registries;

  private MenstruationRegistries(Set<MenstruationRegistry> registries) {
    Objects.requireNonNull(registries);
    this.registries = registries;
  }

  public static MenstruationRegistries of() {
    return new MenstruationRegistries(new HashSet<>());
  }

  public static MenstruationRegistries of(Set<MenstruationRegistry> registries) {
    return new MenstruationRegistries(registries);
  }

  public MenstruationRegistry addRegistry(RegistryMenstruationType type, LocalDate date)
      throws ExistingRegistryException, InvalidRegistryDateException {
    MenstruationRegistry registry = MenstruationRegistry.of(type, date);

    // No future dates are added
    if (date.isAfter(LocalDate.now())) {
      throw new InvalidRegistryDateException();
    }

// mirar TreeMap, MenstruationRegistry -> comparable por fecha
// last came, then went
//    // last went, then came
//    // save registry -> last is same type? -> invalid type
//    Set orderedRegistries = new TreeSet(registries);

    // No same registry
    if (!registries.add(registry)) {
      throw new ExistingRegistryException();
    }

    return registry;
  }

  public class ExistingRegistryException extends Throwable {}

  public class InvalidRegistryDateException extends Throwable {}

  public class InvalidRegistryTypeException extends Throwable {}
}
