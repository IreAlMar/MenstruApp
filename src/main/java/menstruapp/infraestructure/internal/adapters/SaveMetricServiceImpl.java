package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MetricSpecifics;
import menstruapp.application.SaveMetricService;
import menstruapp.domain.metric.Metric;
import menstruapp.domain.metric.MetricDescription;
import menstruapp.domain.metric.MetricRange;
import menstruapp.domain.metric.MetricRecord;
import menstruapp.domain.shared.UniqueIdentifier;
import menstruapp.infraestructure.internal.data.MetricEntity;
import menstruapp.infraestructure.internal.data.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SaveMetricServiceImpl implements SaveMetricService {
  private final MetricRepository metricRepository;

  public SaveMetricServiceImpl(MetricRepository metricRepository) {
    this.metricRepository = metricRepository;
  }

  @Override
  public MetricSpecifics saveMetric(String id, String description, Integer min, Integer max) {
    UniqueIdentifier uniqueIdentifier = UniqueIdentifier.of(UUID.fromString(id));
    MetricDescription metricDescription = new MetricDescription(description);
    MetricRange metricRange = MetricRange.of(min, max);
    List<MetricRecord> metricRecords = new ArrayList<>();
    Metric metric = Metric.of(uniqueIdentifier, metricDescription, metricRange, metricRecords);

    MetricEntity metricEntity = metricRepository.save(MetricEntity.of(metric));
    return new MetricSpecificsImpl(
        metricEntity.getId().toString(),
        metricEntity.getDescription(),
        new MaxAndMinImpl(metricEntity.getMin(), metricEntity.getMax()));
  }
}
