package domain.metric;

import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.stream.IntStream;

public class MetricRange {
    private Integer min;
    private Integer max;
    private IntStream range;

    public static Integer DEFAULT_MAX = 10;
    public static Integer DEFAULT_MIN = 0;

    private MetricRange(@NonNull Integer min, @NonNull Integer max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        this.min = min;
        this.max = max;
        this.range = IntStream.rangeClosed(min, max);
    }

    // Default range
    public static MetricRange of() {
        return new MetricRange(DEFAULT_MIN, DEFAULT_MAX);
    }

    public static MetricRange of(Integer min, Integer max) {
        return new MetricRange(min, max);
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public IntStream getRange() {
        return range;
    }
}
