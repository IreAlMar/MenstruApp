package menstruapp.application.exception;

import menstruapp.application.MetricSpecifics;

public interface SaveMetricService {
    MetricSpecifics saveMetric(String id, String description, Integer min, Integer max);
}
