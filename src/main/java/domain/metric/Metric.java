package domain.metric;

import domain.shared.UniqueIdentifier;

import java.util.List;

public class Metric {

    private UniqueIdentifier id;
    private MetricDescription description;
    private MetricRange range;
    private List<MetricRecord> redords;

}
