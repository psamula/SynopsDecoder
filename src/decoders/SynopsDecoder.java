package decoders;

import domain.SynopsMeasurement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynopsDecoder {
    private final SynopsReader synopsReader;
    private SynopsMeasurement decodedSynops;
    private final List<String> keysEncoded;

    private Map<String, String> keysDecoded = new HashMap<>();
    private Map<String, String> nonIndexedKeysDecoded = new HashMap<>();

    private final int INDEX_OF_LAST_NON_INDEXED = 4;
    private final int INDEX_OF_FIRST_KEY_SECTION = 1;

    public SynopsDecoder(List<String> keysEncoded, SynopsMeasurement synopsMeasurement, SynopsReader synopsReader) {
        this.keysEncoded = keysEncoded;
        this.decodedSynops = synopsMeasurement;
        this.synopsReader = synopsReader;
    }



    public void setSynopsDateAndId(){
        var parts = this.keysEncoded.get(0).split(",");

        int year = Integer.parseInt(parts[1]);
        int month = Integer.parseInt(parts[2]);
        int dayOfMonth = Integer.parseInt(parts[3]);


        this.decodedSynops.setCreated(LocalDate.of(year, month, dayOfMonth));
    }
    public String getSynopsId() {
        var parts = this.keysEncoded.get(0).split(",");
        return parts[6];
    }
    public SynopsMeasurement getResult() {
        this.start();

        return this.decodedSynops;
    }
    public void start() {
        nonIndexedKeysDecoded.put(-1 + "", getSynopsId());
        int selectedSectionKeyId = 0;

        for (int i = INDEX_OF_FIRST_KEY_SECTION; i <= INDEX_OF_LAST_NON_INDEXED; i++) {
            String translation = this.synopsReader
                    .decodeSectionKey(selectedSectionKeyId + "", keysEncoded.get(i), false);
            nonIndexedKeysDecoded.put(selectedSectionKeyId + "", translation);
            selectedSectionKeyId++;
        }

        String currentlyEncodedKeySection = keysEncoded.get(INDEX_OF_LAST_NON_INDEXED + 1);
        String idOfCurrentlyEncodedKeySection = getIdOfEncodedSectionKey(currentlyEncodedKeySection);
        String rawEncodedKey;
        if (idOfCurrentlyEncodedKeySection.equals("" + 0)) {
            rawEncodedKey = deindexSectionKey(currentlyEncodedKeySection, 2);
        }
        else {
            rawEncodedKey = deindexSectionKey(currentlyEncodedKeySection);
        }

        String translation = this.synopsReader.decodeSectionKey(idOfCurrentlyEncodedKeySection, rawEncodedKey, true);
        keysDecoded.put(idOfCurrentlyEncodedKeySection, translation);

        for (int i = INDEX_OF_LAST_NON_INDEXED + 2; i < getSynopSize() - 1; i++) {
            currentlyEncodedKeySection = keysEncoded.get(i);
            idOfCurrentlyEncodedKeySection = getIdOfEncodedSectionKey(currentlyEncodedKeySection);
            rawEncodedKey = deindexSectionKey(currentlyEncodedKeySection);
            translation = this.synopsReader
                    .decodeSectionKey(idOfCurrentlyEncodedKeySection, rawEncodedKey, true);
            keysDecoded.put(idOfCurrentlyEncodedKeySection, translation);
        }
        currentlyEncodedKeySection = keysEncoded.get(getSynopSize() - 1);
        idOfCurrentlyEncodedKeySection = getIdOfEncodedSectionKey(currentlyEncodedKeySection);
        rawEncodedKey = deindexSectionKey(currentlyEncodedKeySection).replace("=", "");
        translation = this.synopsReader
                .decodeSectionKey(idOfCurrentlyEncodedKeySection, rawEncodedKey, true);
        keysDecoded.put(idOfCurrentlyEncodedKeySection, translation);

        decodedSynops.setNonIndexedKeysDecoded(nonIndexedKeysDecoded);
        decodedSynops.setKeysDecoded(keysDecoded);
        this.setSynopsDateAndId();
    }
    private String deindexSectionKey(String keyEncoded) {
        return keyEncoded.substring(1);
    }
    private String deindexSectionKey(String keyEncoded, int from) {
        return keyEncoded.substring(from);
    }
    private String getIdOfEncodedSectionKey(String keyEncoded) {
        return keyEncoded.charAt(0) + "";
    }
    private int getSynopSize() {
        int cntr = 0;
        for (String s : this.keysEncoded) {
            if (s.equals("333")) {
                return cntr;
            }
            cntr++;
        }
        return cntr;
    }
}
