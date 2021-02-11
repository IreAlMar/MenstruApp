package menstruapp.domain.framework;

import menstruapp.application.ValidationException;

public interface Handler<T,Y> {
  T handle(Y object) throws ValidationException;
}
