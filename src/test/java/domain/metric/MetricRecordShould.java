package domain.metric;

import domain.shared.UniqueIdentifier;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MetricRecordShould {
    PodamFactory factory = new PodamFactoryImpl();

    @Test
    public void createValidRecord(){
        MetricRecord metricRecord = factory.manufacturePojo(MetricRecord.class);
        // TODO look PODAM to select inserted values
        assertEquals(metricRecord, MetricRecord.of(UniqueIdentifier.of(), new MetricValue(), new Date()));
    }

}