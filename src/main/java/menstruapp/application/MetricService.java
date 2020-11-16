package menstruapp.application;

import menstruapp.application.exception.InvalidIdException;

public interface MetricService {
    MaxAndMin getMetricRange(String id) throws InvalidIdException;
}
