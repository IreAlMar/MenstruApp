package menstruapp.domain.metric;

public class MetricDescription {
    String metricDescription;
    
    public MetricDescription(String metricDescription) {
        validate(metricDescription);
        this.metricDescription = metricDescription;
    }

    private void validate(String metricDescription) {
    }
}
