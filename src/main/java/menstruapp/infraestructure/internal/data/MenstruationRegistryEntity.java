package menstruapp.infraestructure.internal.data;

import menstruapp.domain.menstruation.RegistryMenstruationType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class MenstruationRegistryEntity {
  @Id private UUID id;
  private RegistryMenstruationType type;
  private LocalDate date;

  public MenstruationRegistryEntity() {}

  public MenstruationRegistryEntity(UUID id, RegistryMenstruationType type, LocalDate date) {
    this.id = id;
    this.type = type;
    this.date = date;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public RegistryMenstruationType getType() {
    return type;
  }

  public void setType(RegistryMenstruationType type) {
    this.type = type;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
