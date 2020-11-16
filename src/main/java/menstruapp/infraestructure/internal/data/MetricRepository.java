package menstruapp.infraestructure.internal.data;

import menstruapp.domain.metric.Metric;

public interface MetricRepository {
    public Metric getMetric(String id);
}
