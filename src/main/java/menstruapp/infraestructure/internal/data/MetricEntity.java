package menstruapp.infraestructure.internal.data;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class MetricEntity {

    @Id
    private UUID id;
    private String description;
    private Integer max;
    private Integer min;
    // another entity : private List<MetricRecord> records;

    public MetricEntity(UUID id, String description, Integer max, Integer min) {
        this.id = id;
        this.description = description;
        this.max = max;
        this.min = min;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMin() {
        return min;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
