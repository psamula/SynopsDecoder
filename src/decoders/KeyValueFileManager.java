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
    private List<SectionKey> nonIndexedKeys;
    private Map<String, SectionKey> indexedKeys;
    private Map<String/*File*/, Map<String, String>> keyValueDerivedFromFile;
    private Scanner sc;
    List<Integer> indexesOfSectionsWithFiles
    public KeyValueFileManager(Scanner sc) {
    }

    private void assignMapToSectionKey() throws FileNotFoundException {
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
}
