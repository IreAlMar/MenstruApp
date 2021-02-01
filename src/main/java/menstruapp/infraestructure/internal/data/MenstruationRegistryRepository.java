package menstruapp.infraestructure.internal.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenstruationRegistryRepository
    extends CrudRepository<MenstruationRegistryEntity, UUID> {}
