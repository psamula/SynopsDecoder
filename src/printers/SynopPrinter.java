package printers;

import dao.SynopsKeysProvider;
import domain.SynopsMeasurement;
import domain.section_keys.SectionKey;

import java.util.HashMap;
import java.util.Map;

public class SynopPrinter {
    public static void printer(SynopsKeysProvider synopsKeysProvider, SynopsMeasurement synopsMeasurement) {
        Map<String, SectionKey> indexedKeysMap = synopsKeysProvider.getIndexedKeysMap();
        HashMap<String, SectionKey> nonIndexedKeysMap = synopsKeysProvider.getNonIndexedKeysMap();

        System.out.println("Data pomiaru: " + synopsMeasurement.getCreated());
        for (int i = -1; i < nonIndexedKeysMap.size() - 1; i++) {
            var sectionKey = nonIndexedKeysMap.getOrDefault("" + i, null);
            if (sectionKey == null) {
                System.out.println("Brak sekcji nr " + i);
            } else {
                System.out.println("\nKryterium: " + sectionKey.getName() + " - (klucz nr " + (i + 1) + ")");
                System.out.println("\nWartosc: \n" + synopsMeasurement.getNonIndexedKeysDecoded().getOrDefault("" + i, "---- BRAK -----"));
            }
            System.out.println("--------------------------------------------------------");
        }
        for (int i = 0; i < indexedKeysMap.size(); i++) {
            var sectionKey = indexedKeysMap.getOrDefault("" + i, null);
            if (sectionKey == null) {
                System.out.println("Break sekcji nr " + i);
            } else {
                System.out.println("\nKryterium: " + sectionKey.getName() + " - (klucz nr " + (i + nonIndexedKeysMap.size()) + ")");
                System.out.println("\nWartosc: \n" + synopsMeasurement.getKeysDecoded().getOrDefault("" + i, "---- BRAK -----"));
            }
            System.out.println("--------------------------------------------------------");
        }
    }
}
