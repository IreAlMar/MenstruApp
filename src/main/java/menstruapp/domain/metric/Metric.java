package menstruapp.domain.metric;

import menstruapp.domain.shared.RecordDate;
import menstruapp.domain.shared.UniqueIdentifier;

import java.util.List;
import java.util.Objects;

public class Metric {

    private UniqueIdentifier id;
    private MetricDescription description;
    private MetricRange range;
    private List<MetricRecord> records;

    private Metric(UniqueIdentifier id, MetricDescription description, MetricRange range,
                   List<MetricRecord> records) {
        if (Objects.isNull(id) || Objects.isNull(description)|| Objects.isNull(range)|| Objects.isNull(records)) {
            throw new IllegalArgumentException("Attributes should not be null");
        }

        this.id = id;
        this.description = description;
        this.range = range;
        this.records = records;
    }

    public static Metric of(UniqueIdentifier id, MetricDescription description, MetricRange range,
                            List<MetricRecord> records) {
        return new Metric(id, description, range, records);
    }

    public List<MetricRecord> addRecord(MetricValue metricValue, RecordDate date) throws
            InvalidMetricValueException {
        validateMetricValue(metricValue);
        MetricRecord.of(UniqueIdentifier.of(), metricValue, date);
        return records;
    }

    private void validateMetricValue(MetricValue metricValue) throws InvalidMetricValueException {
        if (!range.getRange().anyMatch(n -> n == metricValue.getValue())) {
            throw new InvalidMetricValueException();
        }
    }

    public UniqueIdentifier getId() {
        return id;
    }

    public MetricDescription getDescription() {
        return description;
    }

    public MetricRange getRange() {
        return range;
    }

    public List<MetricRecord> getRecords() {
        return records;
    }
}
