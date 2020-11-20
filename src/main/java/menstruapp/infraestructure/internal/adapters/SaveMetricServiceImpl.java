package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.MetricSpecifics;
import menstruapp.application.SaveMetricService;
import org.springframework.stereotype.Service;

@Service
public class SaveMetricServiceImpl implements SaveMetricService {
    @Override
    public MetricSpecifics saveMetric(String id, String description, Integer min, Integer max) {
        return null;
    }
}
