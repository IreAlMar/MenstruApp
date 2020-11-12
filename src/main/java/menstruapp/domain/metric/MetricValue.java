package menstruapp.domain.metric;

import java.util.Objects;

public class MetricValue {
    private int value;

    private MetricValue(int value) {
        this.value = value;
    }

    public static MetricValue of(int value) {
        return new MetricValue(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricValue that = (MetricValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
