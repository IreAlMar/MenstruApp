package menstruapp.infraestructure.internal.data;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class MetricEntity {

  @Id
  private UUID id;
  private String description;
  private Integer min;
  private Integer max;
  // another entity : private List<MetricRecord> records;

  public MetricEntity(UUID id, String description, Integer min, Integer max) {
    this.id = id;
    this.description = description;
    this.min = min;
    this.max = max;
  }

  public UUID getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Integer getMin() {
    return min;
  }

  public Integer getMax() {
    return max;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setMin(Integer min) {
    this.min = min;
  }

  public void setMax(Integer max) {
    this.max = max;
  }
}
