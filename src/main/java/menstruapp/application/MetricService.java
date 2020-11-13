package menstruapp.application;

import menstruapp.domain.metric.MetricRange;
import menstruapp.domain.shared.UniqueIdentifier;
import menstruapp.application.exception.InvalidIdException;

public interface MetricService {
    MetricRange getMetricRange(UniqueIdentifier id) throws InvalidIdException;
}
