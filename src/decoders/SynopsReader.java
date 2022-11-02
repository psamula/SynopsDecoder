package decoders;

import dao.SynopsKeysProvider;
import file_managers.KeyValueFileManager;
import domain.section_keys.SectionKey;
import domain.section_keys.SubkeyDetails;

import java.util.Map;

public class SynopsReader {
    private final KeyValueFileManager keyValueFileManager;
    private final SynopsKeysProvider keysProvider;

    public SynopsReader(KeyValueFileManager keyValueFileManager, SynopsKeysProvider keysProvider) {
        this.keyValueFileManager = keyValueFileManager;
        this.keysProvider = keysProvider;
    }



    public String decodeSectionKey(String sectionKeyId, String encodedKeyStr, boolean isIndexed) {
        SectionKey sectionKey;
        if (isIndexed) {
            sectionKey = this.keysProvider.getIndexedKeysMap().getOrDefault(sectionKeyId, null);
        } else{
            sectionKey = this.keysProvider.getNonIndexedKeysMap().getOrDefault(sectionKeyId, null);
        }
        var subkeys = sectionKey.getSubkeyDetails();

        int charInEncodedStringTracker = 0;
        var translatedSection = new StringBuilder();

        for (var sbk : subkeys) {
            var wordToTranslate = new StringBuilder(); //formed of subkey
            int charsPerInfo = sbk.getCharsPerSubkey();
            var encodedKey = encodedKeyStr.toCharArray();
            for(int i = 0; i < charsPerInfo; i++) {
                wordToTranslate.append(encodedKey[charInEncodedStringTracker]);
                charInEncodedStringTracker++;
            }
            String translation = translate(sectionKeyId, wordToTranslate.toString(), sbk, isIndexed);
            if (translation.equals("")) {
                continue;
            }
            translatedSection.append(translation).append(", ");
        }
        return translatedSection.toString().substring(0, translatedSection.length() - 2);
    }

    private String translate(String sectionKeyId, String wordToTranslate, SubkeyDetails subkey, boolean isIndexed) {
        if (subkey.isDirectValue()) {
            return wordToTranslate;
        }
        Map<String, String> sectionMap;
        if (isIndexed) {
            sectionMap = this.keyValueFileManager.getDecoderMapForSection(sectionKeyId);
        }
        else {
            sectionMap = this.keyValueFileManager.getDecoderMapForNonIndexedSection(sectionKeyId);
        }
        var translation = sectionMap.getOrDefault(wordToTranslate, null);

        return (translation == null || sectionMap == null) ? "" : translation;
    }
}
