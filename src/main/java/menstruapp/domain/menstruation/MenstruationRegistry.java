package menstruapp.domain.menstruation;

import java.time.LocalDate;
import java.util.Objects;

public class MenstruationRegistry {
    private final RegistryMenstruationType type;
    private final LocalDate date;

    private MenstruationRegistry(RegistryMenstruationType type, LocalDate date) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(date);
        this.type = type;
        this.date = date;
    }

    protected static MenstruationRegistry of(RegistryMenstruationType type, LocalDate date){
        return new MenstruationRegistry(type, date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenstruationRegistry registry = (MenstruationRegistry) o;
        return type == registry.type &&
                Objects.equals(date, registry.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date);
    }

    public RegistryMenstruationType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }
}
