package file_managers;

import dao.SynopsKeysProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KeyValueFileManager {
    private final SynopsKeysProvider synopsKeysProvider;
    private Map<String, Map<String, String>> keyValueDerivedFromFile = new HashMap<>();
    private Map<String, Map<String, String>> keyValueNonIndexedDerivedFromFile = new HashMap<>();
    private Scanner sc;


    public KeyValueFileManager(SynopsKeysProvider synopsKeysProvider) throws FileNotFoundException {
        this.synopsKeysProvider = synopsKeysProvider;
        assignMapToSectionKey();
    }

    private void assignMapToSectionKey() throws FileNotFoundException {
        this.keyValueNonIndexedDerivedFromFile = this.synopsKeysProvider.getNonIndexedKeysMap().entrySet().stream()
                .filter(e -> e.getValue().getSubkeyDetails().stream().anyMatch(b -> b.isDirectValue() == false))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> {
                    try {
                        return createKeyValueFromFile(String.format("src/section_keys_descriptions/unindexed/uk%s.txt", e.getKey()));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }));

        this.keyValueDerivedFromFile = this.synopsKeysProvider.getIndexedKeysMap().entrySet().stream()
                .filter(e -> e.getValue().getSubkeyDetails().stream().anyMatch(b -> b.isDirectValue() == false))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> {
                    try {
                        return createKeyValueFromFile(String.format("src/section_keys_descriptions/s1k%s.txt", e.getKey()));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }));

    }
    private Map<String, String> createKeyValueFromFile(String filename) throws FileNotFoundException {
        var mapFromFile = new HashMap<String, String>();
        this.sc = new Scanner(new File (filename));
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            var parts = line.split(" - ");
            mapFromFile.put(parts[0], parts[1]);
        }
        return mapFromFile;
    }
    public Map<String, String> getDecoderMapForNonIndexedSection(String key) {
        return keyValueNonIndexedDerivedFromFile.getOrDefault(key, null);
    }
    public Map<String, String> getDecoderMapForSection(String key) {
        return keyValueDerivedFromFile.getOrDefault(key, null);
    }
}
