package menstruapp.application;

import menstruapp.application.exception.InvalidIdException;

public interface MetricRangeService {
    MaxAndMin getMetricRange(String id) throws InvalidIdException;
}
