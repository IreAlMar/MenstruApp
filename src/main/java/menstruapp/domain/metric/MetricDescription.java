package menstruapp.domain.metric;

public class MetricDescription {
    private final String metricDescription;
    
    public MetricDescription(String metricDescription) {
        validate(metricDescription);
        this.metricDescription = metricDescription;
    }

    private void validate(String metricDescription) {
    }

    public String getMetricDescription() {
        return metricDescription;
    }

    @Override
    public String toString() {
        return metricDescription;
    }
}
