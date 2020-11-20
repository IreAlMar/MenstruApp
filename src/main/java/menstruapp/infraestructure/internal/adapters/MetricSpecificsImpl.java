package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MaxAndMin;
import menstruapp.application.MetricSpecifics;

// DTO?
public class MetricSpecificsImpl implements MetricSpecifics {

  private final String id;
  private final String description;
  private final MaxAndMinImpl maxAndMin;

  public MetricSpecificsImpl(String id, String description, MaxAndMinImpl maxAndMin) {
    this.id = id;
    this.description = description;
    this.maxAndMin = maxAndMin;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public MaxAndMin getMaxAndMin() {
    return maxAndMin;
  }
}
