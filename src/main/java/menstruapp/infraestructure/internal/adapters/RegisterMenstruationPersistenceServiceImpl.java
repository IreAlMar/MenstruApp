package menstruapp.infraestructure.internal.adapters;

import menstruapp.application.registermenstruation.RegisterMenstruationPersistenceService;
import menstruapp.domain.menstruation.MenstruationRegistry;
import menstruapp.infraestructure.internal.data.MenstruationRegistryEntity;
import menstruapp.infraestructure.internal.data.MenstruationRegistryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterMenstruationPersistenceServiceImpl
    implements RegisterMenstruationPersistenceService {
  private final MenstruationRegistryRepository menstruationRegistryRepository;

  public RegisterMenstruationPersistenceServiceImpl(
      MenstruationRegistryRepository menstruationRegistryRepository) {
    this.menstruationRegistryRepository = menstruationRegistryRepository;
  }

  @Override
  public void add(UUID uuid, MenstruationRegistry menstruationRegistry) {
     MenstruationRegistryEntity menstruationRegistryEntity =
        new MenstruationRegistryEntity(
            uuid, menstruationRegistry.getType(), menstruationRegistry.getDate());
    menstruationRegistryRepository.save(menstruationRegistryEntity);
    // if error -> throw exception
  }
}
