package menstruapp.application;
import menstruapp.application.MetricSpecifics;
import menstruapp.domain.metric.InvalidMetricValueException;

public interface SaveMetricService {
    MetricSpecifics saveMetric(String id, String description, Integer min, Integer max) throws InvalidMetricValueException;
}