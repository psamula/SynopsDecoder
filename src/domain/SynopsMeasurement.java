package domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SynopsMeasurement {
    private LocalDate created;
    private Map<String, String> nonIndexedKeysDecoded = new HashMap<>();
    private Map<String, String> keysDecoded = new HashMap<>();

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public Map<String, String> getNonIndexedKeysDecoded() {
        return nonIndexedKeysDecoded;
    }

    public void setNonIndexedKeysDecoded(Map<String, String> nonIndexedKeysDecoded) {
        this.nonIndexedKeysDecoded = nonIndexedKeysDecoded;
    }

    public Map<String, String> getKeysDecoded() {
        return keysDecoded;
    }

    public void setKeysDecoded(Map<String, String> keysDecoded) {
        this.keysDecoded = keysDecoded;
    }

    public String toString() {
        return this.nonIndexedKeysDecoded.values() + "\n\n\n\n" + this.keysDecoded.values();
    }
}
