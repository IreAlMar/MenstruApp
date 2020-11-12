package menstruapp.application;

import menstruapp.domain.metric.MetricRange;
import menstruapp.domain.shared.UniqueIdentifier;

public interface MetricService {
    MetricRange getMetricRange(UniqueIdentifier id);
}
