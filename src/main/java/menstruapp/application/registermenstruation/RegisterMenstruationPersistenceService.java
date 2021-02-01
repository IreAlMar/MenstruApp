package menstruapp.application.registermenstruation;

import menstruapp.domain.menstruation.MenstruationRegistry;

import java.util.UUID;

public interface RegisterMenstruationPersistenceService {
    void add(UUID uuid, MenstruationRegistry menstruationRegistry);
}
