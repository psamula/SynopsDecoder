package decoders;

import section_keys.SectionKey;

import java.io.File;
import java.util.List;
import java.util.Map;

public class KeyValueFileManager {
    private List<File> files;
    private List<SectionKey> nonIndexedKeys;
    private Map<String, SectionKey> indexedKeys;
    private Map<String/*File*/, Map<String, String>> KeyValueDerivedFromFile;

    private void createKeyValueFromFiles(int startSectionIndex, int lastSectionIndex) {
        for (int i = startSectionIndex; i <= lastSectionIndex; i++) {
           var section = indexedKeys.get(i);
        }
    }
}
