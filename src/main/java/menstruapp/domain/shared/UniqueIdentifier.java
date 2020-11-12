package menstruapp.domain.shared;

import java.util.Objects;
import java.util.UUID;

public class UniqueIdentifier {
    private UUID id;

    private UniqueIdentifier(UUID id) {
        this.id = id;
    }

    public static UniqueIdentifier of(UUID id){
        return new UniqueIdentifier(id);
    }

    public static UniqueIdentifier of(){
        return new UniqueIdentifier(UUID.randomUUID() );
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniqueIdentifier that = (UniqueIdentifier) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
