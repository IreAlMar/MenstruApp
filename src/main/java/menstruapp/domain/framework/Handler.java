package menstruapp.domain.framework;

import menstruapp.application.exception.ValidationException;
import menstruapp.domain.menstruation.MenstruationRegistry;

import java.util.TreeSet;

public interface Handler<T,Y> {
  TreeSet<MenstruationRegistry> handle(Y object) throws ValidationException;
}
