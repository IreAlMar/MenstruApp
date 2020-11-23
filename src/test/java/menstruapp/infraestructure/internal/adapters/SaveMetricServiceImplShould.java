package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MetricSpecifics;
import menstruapp.infraestructure.internal.data.MetricEntity;
import menstruapp.infraestructure.internal.data.MetricRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class SaveMetricServiceImplShould {

  public SaveMetricServiceImpl service;
  @Mock public MetricRepository metricRepository;
  PodamFactory factory = new PodamFactoryImpl();
  MetricEntity metricEntity = factory.manufacturePojo(MetricEntity.class);

  @BeforeEach
  public void setup() {
    service = new SaveMetricServiceImpl(metricRepository);
  }

  @Test
  public void saveValidMetric() {
    metricEntity.setMin(0);
    metricEntity.setMax(10);

    Mockito.when(metricRepository.save(any(MetricEntity.class))).thenReturn(metricEntity);
    MetricSpecifics metricSpecifics =
        service.saveMetric(
            metricEntity.getId().toString(),
            metricEntity.getDescription(),
            metricEntity.getMin(),
            metricEntity.getMax());
    assertNotNull(metricSpecifics);
    assertEquals(metricSpecifics.getId(), metricEntity.getId().toString());
    assertEquals(metricSpecifics.getDescription(), metricEntity.getDescription());
    assertEquals(metricSpecifics.getMaxAndMin().getMin(), metricEntity.getMin());
    assertEquals(metricSpecifics.getMaxAndMin().getMax(), metricEntity.getMax());
  }

  @Test
  public void throwExceptionWhenSavingInvalidMetric() {
    metricEntity.setMin(11);
    metricEntity.setMax(10);

    assertThrows(
        IllegalArgumentException.class,
        () ->
            service.saveMetric(
                metricEntity.getId().toString(),
                metricEntity.getDescription(),
                metricEntity.getMin(),
                metricEntity.getMax()));
  }
}
