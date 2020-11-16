package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MaxAndMin;

public class MaxAndMinImpl implements MaxAndMin {
  private final Integer max;
  private final Integer min;

  // TODO was protected but need to make it public to access it from the tests
  public MaxAndMinImpl(Integer min, Integer max) {
    this.max = max;
    this.min = min;
  }

  @Override
  public Integer getMin() {
    return min;
  }

  @Override
  public Integer getMax() {
    return max;
  }
}
