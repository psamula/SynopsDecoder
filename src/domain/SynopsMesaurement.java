package domain;

import java.time.LocalDate;
import java.util.List;

public class SynopsMesaurement {
    private LocalDate created;
    private List<String> keysDecoded;

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public List<String> getKeysDecoded() {
        return keysDecoded;
    }

    public void setKeysDecoded(List<String> keysDecoded) {
        this.keysDecoded = keysDecoded;
    }
}
