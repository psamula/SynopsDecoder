package decoders;

import section_keys.SectionKey;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class KeyValueFileManager {
    private Map<String, File> filesMap;
    private Map<String, SectionKey> nonIndexedKeys;
    private Map<String, SectionKey> indexedKeys;
    private Map<String, Map<String, String>> keyValueDerivedFromFile;
    private Map<String, Map<String, String>> keyValueNonIndexedDerivedFromFile;
    private Scanner sc;

    public Map<String, Map<String, String>> getKeyValueDerivedFromFile() {
        return keyValueDerivedFromFile;
    }

    public void setKeyValueDerivedFromFile(Map<String, Map<String, String>> keyValueDerivedFromFile) {
        this.keyValueDerivedFromFile = keyValueDerivedFromFile;
    }

    public Map<String, Map<String, String>> getKeyValueNonIndexedDerivedFromFile() {
        return keyValueNonIndexedDerivedFromFile;
    }

    public void setKeyValueNonIndexedDerivedFromFile(Map<String, Map<String, String>> keyValueNonIndexedDerivedFromFile) {
        this.keyValueNonIndexedDerivedFromFile = keyValueNonIndexedDerivedFromFile;
    }

    private final int INDEX_OF_LAST_NON_INDEXED_KEY = 4;

    public KeyValueFileManager(Map<String, SectionKey> nonIndexedKeys, Map<String, SectionKey> indexedKeys) {
        this.nonIndexedKeys = nonIndexedKeys;
        this.indexedKeys = indexedKeys;
    }

    private void assignMapToSectionKey() throws FileNotFoundException {
        var nonIndexedSectionsWithFilesSupport = this.nonIndexedKeys.entrySet().stream()
                .filter(e -> e.getValue().getSubkeyDetails().stream().anyMatch(b -> b.isDirectValue() == false))
                .collect(Collectors.toList());

        for (int i = 0; i < nonIndexedSectionsWithFilesSupport.size(); i++) {
            this.keyValueNonIndexedDerivedFromFile.put("" + i, createKeyValueFromFile(String.format("uk%s.txt", i)));
        }

       var sectionsWithFilesSupport =  this.indexedKeys.entrySet().stream()
                .filter(e -> e.getValue().getSubkeyDetails().stream().anyMatch(b -> b.isDirectValue() == false))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (var c : sectionsWithFilesSupport.entrySet()) {
            this.keyValueDerivedFromFile.put(c.getKey(), createKeyValueFromFile(String.format("s1k%s.txt", c.getKey())));
        }
    }
    private Map<String, String> createKeyValueFromFile(String filename) throws FileNotFoundException {
        var mapFromFile = new HashMap<String, String>();
        this.sc = new Scanner(new File (filename));
        String line;
        while ((line = sc.nextLine()) != null) {
            var parts = line.split(" - ");
            mapFromFile.put(parts[0], parts[1]);
        }
        return mapFromFile;
    }
    public Map<String, String> getDecoderMapForNonIndexedSection(String key) {
        return keyValueNonIndexedDerivedFromFile.get(key);
    }
    public Map<String, String> getDecoderMapForSection(String key) {
        return keyValueDerivedFromFile.get(key);
    }
}
