package menstruapp.domain.metric;

import menstruapp.domain.shared.InvalidRecordDateException;
import menstruapp.domain.shared.RecordDate;
import menstruapp.domain.shared.UniqueIdentifier;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MetricRecordShould {
    PodamFactory factory = new PodamFactoryImpl();
    MetricRecord metricRecordMother = factory.manufacturePojo(MetricRecord.class);

    @Test
    public void createValidRecord() throws InvalidRecordDateException {
        MetricRecord metricRecord = MetricRecord.of(
                UniqueIdentifier.of(metricRecordMother.getRecordId().getId()),
                MetricValue.of(metricRecordMother.getMetricValue().getValue()),
                RecordDate.of(metricRecordMother.getRecordDate().getDate())
        );

        assertNotNull(metricRecordMother, "The metric record mother cannot be null!");
        assertNotNull(metricRecord, "The metric record cannot be null!");
        assertEquals(metricRecordMother, metricRecord);
    }

    @Test
    public void neverHaveNullRecordValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            MetricRecord.of(null, null, null);
        });
    }

    @Test
    public void throwExceptionWhenCreatingFutureDateRecord() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 30);
        Date futureDate = calendar.getTime();

        assertThrows(InvalidRecordDateException.class, () -> {
            MetricRecord.of(
                    UniqueIdentifier.of(metricRecordMother.getRecordId().getId()),
                    MetricValue.of(metricRecordMother.getMetricValue().getValue()),
                    RecordDate.of(futureDate)
            );
        });

        assertNotNull(metricRecordMother, "The metric record mother cannot be null!");
    }

    @Test
    public void throwExceptionWhenCreatingOutOfRangeMetricValueRecordMin() {
        Metric metric = factory.manufacturePojo(Metric.class);
        Integer outOfRangeValueMin = metric.getRange().getMin() - 1;

        assertThrows(InvalidMetricValueException.class, () -> {
            metric.addRecord(MetricValue.of(outOfRangeValueMin), RecordDate.of(new Date()));
        });
    }

    @Test
    public void throwExceptionWhenCreatingOutOfRangeMetricValueRecordMax() {
        Metric metric = factory.manufacturePojo(Metric.class);
        Integer outOfRangeValueMax = metric.getRange().getMax() + 1;

        assertThrows(InvalidMetricValueException.class, () -> {
            metric.addRecord(MetricValue.of(outOfRangeValueMax), RecordDate.of(new Date()));
        });
    }
}
