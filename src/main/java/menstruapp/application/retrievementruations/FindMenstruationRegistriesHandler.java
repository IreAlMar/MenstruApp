package menstruapp.application.retrievementruations;

import menstruapp.application.exception.ValidationException;
import menstruapp.application.registermenstruation.RegisterMenstruationPersistenceService;
import menstruapp.domain.framework.Handler;
import menstruapp.domain.menstruation.MenstruationRegistries;
import menstruapp.domain.menstruation.MenstruationRegistry;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
public class FindMenstruationRegistriesHandler implements Handler<Void, FindMenstruationRegistriesQuery> {
    private final MenstruationRegistries menstruationRegistries;
    private final RegisterMenstruationPersistenceService persistenceService;

    public FindMenstruationRegistriesHandler(
            MenstruationRegistries menstruationRegistries,
            RegisterMenstruationPersistenceService persistenceService) {
        this.menstruationRegistries = menstruationRegistries;
        this.persistenceService = persistenceService;
    }

    @Override
    public TreeSet<MenstruationRegistry> handle(FindMenstruationRegistriesQuery object) throws ValidationException {
        return menstruationRegistries.getRegistries();
    }
}
