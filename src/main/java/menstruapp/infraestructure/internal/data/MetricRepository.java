package menstruapp.infraestructure.internal.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MetricRepository extends CrudRepository<MetricEntity, UUID> {}
