package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MaxAndMin;
import menstruapp.application.MetricService;
import menstruapp.application.exception.InvalidIdException;
import menstruapp.domain.metric.Metric;
import menstruapp.infraestructure.internal.data.MetricRepository;
import org.springframework.stereotype.Service;

@Service
public class MetricServiceImpl implements MetricService {
    private final MetricRepository metricRepository;

    public MetricServiceImpl(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @Override
    public MaxAndMin getMetricRange(String id) throws InvalidIdException {
        // TODO UniqueIdentifier.of(request)
        // TODO remove domain dependencies?
        Metric metric = metricRepository.getMetric(id);

        return new MaxAndMinImpl(metric.getRange().getMin(), metric.getRange().getMax());
    }
}
