package domain.metric;

import domain.shared.UniqueIdentifier;

import java.util.Date;
import java.util.Objects;

public class MetricRecord {

    private UniqueIdentifier recordId;
    private MetricValue metricValue;
    private RecordDate date;

    // avoid calling the constructor from anywhere but this class to avoid miss creation
    // default constructor disappears
    private MetricRecord(UniqueIdentifier recordId, MetricValue metricValue, RecordDate date) {
        this.recordId = recordId;
        this.metricValue = metricValue;
        this.date = date;
        // Validation logic, generic to the whole object
    }

    public static MetricRecord of(UniqueIdentifier recordId, MetricValue metricValue, RecordDate date) {
        // Add validation logic to a specific case
        return new MetricRecord(recordId, metricValue, date);
    }

    public UniqueIdentifier getRecordId() {
        return recordId;
    }

    public MetricValue getMetricValue() {
        return metricValue;
    }

    public RecordDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricRecord that = (MetricRecord) o;
        return Objects.equals(recordId, that.recordId) &&
                Objects.equals(metricValue, that.metricValue) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, metricValue, date);
    }

    @Override
    public String toString() {
        return "MetricRecord{" +
                "recordId=" + recordId +
                ", metricValue=" + metricValue +
                ", date=" + date +
                '}';
    }
}
