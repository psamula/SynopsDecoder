package decoders;

import section_keys.SectionKey;
import section_keys.SubkeyDetails;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SynopsReader {
    private final SynopsKeysProvider keysProvider;
    private final KeyValueFileManager keyValueFileManager;

    public SynopsReader(KeyValueFileManager keyValueFileManager, SynopsKeysProvider keysProvider) {
        this.keyValueFileManager = keyValueFileManager;
        this.keysProvider = keysProvider;
    }



    public String decodeSectionKey(String sectionKeyId, String encodedKeyStr, boolean isIndexed) {
        SectionKey sectionKey;
        if (isIndexed) {
            sectionKey = this.keysProvider.getIndexedKeysMap().get(sectionKeyId);
        } else{
            sectionKey = this.keysProvider.getNonIndexedKeysMap().get(sectionKeyId);
        }
        var subkeys = sectionKey.getSubkeyDetails();

        int charInEncodedStringTracker = 0;
        var translatedSection = new StringBuilder();

        for (var sbk : subkeys) {
            var wordToTranslate = new StringBuilder(); //formed of subkey
            int charsPerInfo = sbk.getCharsPerSubkey();
            boolean directValue = sbk.isDirectValue();
            var encodedKey = encodedKeyStr.toCharArray();
            for(int i = 0; i < charsPerInfo; i++) {
                wordToTranslate.append(encodedKey[charInEncodedStringTracker]);
                charInEncodedStringTracker++;
            }
            String translation = translate(sectionKeyId, wordToTranslate.toString(), sbk, isIndexed);
            translatedSection.append(translation + "\n");
        }
        return translatedSection.toString();
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
        var translation = sectionMap.get(wordToTranslate);

        return (translation == null || sectionMap == null) ? "" : translation;
    }
}
