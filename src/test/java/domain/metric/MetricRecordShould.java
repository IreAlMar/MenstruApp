package domain.metric;

import domain.shared.UniqueIdentifier;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MetricRecordShould {
    PodamFactory factory = new PodamFactoryImpl();

    @Test
    public void createValidRecord() {
        MetricRecord metricRecordMother = factory.manufacturePojo(MetricRecord.class);
        MetricRecord metricRecord = MetricRecord.of(
                UniqueIdentifier.of(metricRecordMother.getRecordId().getId()),
                MetricValue.of(metricRecordMother.getMetricValue().getValue()),
                RecordDate.of(metricRecordMother.getDate().getDate()
                )
        );

        assertNotNull(metricRecordMother, "The metric record mother cannot be null!");
        assertNotNull(metricRecord, "The metric record cannot be null!");
        assertEquals(metricRecordMother, metricRecord);
    }

}