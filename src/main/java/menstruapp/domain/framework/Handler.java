package menstruapp.domain.framework;

public interface Handler<T,Y> {
  T handle(Y object);
}
