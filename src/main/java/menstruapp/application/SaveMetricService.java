package menstruapp.application;
import menstruapp.application.MetricSpecifics;

public interface SaveMetricService {
    MetricSpecifics saveMetric(String id, String description, Integer min, Integer max);
}