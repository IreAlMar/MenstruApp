package menstruapp.domain.metric;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MetricShould {

  @Test
  public void neverHaveNullValues() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Metric.of(null, null, null, null);
        });
  }
}
