package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MaxAndMin;
import menstruapp.application.exception.InvalidIdException;
import menstruapp.infraestructure.internal.data.MetricEntity;
import menstruapp.infraestructure.internal.data.MetricRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MetricRangeServiceImplShould {
  PodamFactory factory = new PodamFactoryImpl();
  MetricEntity metricEntity = factory.manufacturePojo(MetricEntity.class);

  public MetricRangeServiceImpl service;
  @Mock public MetricRepository metricRepository;

  @BeforeEach
  public void setup() {
    service = new MetricRangeServiceImpl(metricRepository);
  }

  @Test
  public void retrieveMetricRange() throws InvalidIdException {
    Mockito.when(metricRepository.findById(any(UUID.class))).thenReturn(Optional.of(metricEntity));
    MaxAndMin maxAndMin = service.getMetricRange(UUID.randomUUID().toString());
    assertNotNull(maxAndMin);
    assertEquals(metricEntity.getMin(), maxAndMin.getMin());
    assertEquals(metricEntity.getMax(), maxAndMin.getMax());
  }

  @Test
  public void throwExceptionWhenInvalidId() {
    Mockito.when(metricRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
    assertThrows(
        InvalidIdException.class, () -> service.getMetricRange(UUID.randomUUID().toString()));
  }
}
