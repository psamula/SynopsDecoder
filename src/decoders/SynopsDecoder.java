package decoders;

import domain.SynopsMesaurement;
import section_keys.SectionKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SynopsDecoder {
    private List<String> keysEncoded;
    private SynopsKeysProvider keysProvider;
    private SynopsMesaurement decodedSynops;
    private List<String> decodedValues = new ArrayList<>();
    private final int INDEX_OF_LAST_NON_INDEXED = 4;

    public SynopsDecoder(List<String> keysEncoded, SynopsKeysProvider keysProvider, SynopsMesaurement synopsMesaurement) {
        this.keysEncoded = keysEncoded;
        this.keysProvider = keysProvider;
        this.decodedSynops = synopsMesaurement;
    }
    private String deindexKey(String key) {
        return key.substring(1);
    }
    private String deindexKey(String key, int from) {
        return key.substring(from);
    }


    public void setSynopsDate(){
        var parts = this.keysEncoded.get(0).split(",");

        int year = Integer.parseInt(parts[1]);
        int month = Integer.parseInt(parts[2]);
        int dayOfMonth = Integer.parseInt(parts[3]);


        this.decodedSynops.setCreated(LocalDate.of(year, month, dayOfMonth));
    }
    public void handleNonIndexedKeys() {

    }
}
