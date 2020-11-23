package menstruapp.application;

//TODO IllegalArgumentException or custom exception?
public interface SaveMetricService {
    MetricSpecifics saveMetric(String id, String description, Integer min, Integer max) throws IllegalArgumentException;
}