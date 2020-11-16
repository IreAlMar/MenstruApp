package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MaxAndMin;
import menstruapp.application.MetricService;
import menstruapp.application.exception.InvalidIdException;
import menstruapp.infraestructure.internal.data.MetricEntity;
import menstruapp.infraestructure.internal.data.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MetricServiceImpl implements MetricService {
  private final MetricRepository metricRepository;

  public MetricServiceImpl(MetricRepository metricRepository) {
    this.metricRepository = metricRepository;
  }

  @Override
  public MaxAndMin getMetricRange(String id) throws InvalidIdException {

    Optional<MetricEntity> metric = metricRepository.findById(UUID.fromString(id));

    if (metric == null) {
      throw new InvalidIdException();
    }

    try {
      metric.get();
    } catch (Exception e) {
      throw new InvalidIdException();
    }

    return new MaxAndMinImpl(metric.get().getMin(), metric.get().getMax());
  }
}
