package domain.metric;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricRangeShould {

    @Test
    public void neverHaveNullAttributes() {
        assertThrows(NullPointerException.class, () -> {
            MetricRange.of(null, null);
        });
    }

    @Test
    public void createDefaultRange() {
        MetricRange metricRange = MetricRange.of();

        assertEquals(metricRange.getMin(), MetricRange.DEFAULT_MIN);
        assertEquals(metricRange.getMax(), MetricRange.DEFAULT_MAX);
    }

    @Test
    public void throwExceptionWhenMinEqualToMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            MetricRange.of(5, 5);
        });
    }

    @Test
    public void throwExceptionWhenMinGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            MetricRange.of(5, 4);
        });
    }


}