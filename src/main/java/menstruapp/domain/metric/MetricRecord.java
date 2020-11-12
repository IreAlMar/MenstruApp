package menstruapp.domain.metric;

import menstruapp.domain.shared.RecordDate;
import menstruapp.domain.shared.UniqueIdentifier;

import java.util.Objects;

public class MetricRecord {

    private UniqueIdentifier recordId;
    private MetricValue metricValue;
    private RecordDate recordDate;

    // private constructor to avoid calling it from anywhere but this class to avoid object miss
    // creation -> default constructor disappears
    private MetricRecord(UniqueIdentifier recordId, MetricValue metricValue, RecordDate recordDate) {
        if (Objects.isNull(recordId) || Objects.isNull(metricValue) || Objects.isNull(recordDate)) {
            throw new IllegalArgumentException("Attributes should not be null");
        }

        this.recordId = recordId;
        this.metricValue = metricValue;
        this.recordDate = recordDate;
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

    public RecordDate getRecordDate() {
        return recordDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricRecord that = (MetricRecord) o;
        return Objects.equals(recordId, that.recordId) &&
                Objects.equals(metricValue, that.metricValue) &&
                Objects.equals(recordDate, that.recordDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, metricValue, recordDate);
    }

    @Override
    public String toString() {
        return "MetricRecord{" +
                "recordId=" + recordId +
                ", metricValue=" + metricValue +
                ", date=" + recordDate +
                '}';
    }
}
