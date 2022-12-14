package dao;

import domain.section_keys.SectionKey;
import domain.section_keys.SubkeyDetails;

import java.util.*;

public class SynopsKeysProvider {
    private List<SectionKey> indexedKeysList;
    private List<SectionKey> nonIndexedKeysList;
    private Map<String, SectionKey> indexedKeysMap;
    private HashMap<String, SectionKey> nonIndexedKeysMap;

    public SynopsKeysProvider() {
        this.indexedKeysList = new ArrayList<>();
        this.nonIndexedKeysList = new ArrayList<>();
        fillAllSectionKeysLists();
        fillIndexedKeysMap();
        fillNonIndexedKeysMap();
    }

    public List<SectionKey> getNonIndexedKeysList() {
        return nonIndexedKeysList ;
    }

    public HashMap<String, SectionKey> getNonIndexedKeysMap() {
        return nonIndexedKeysMap;
    }

    public void setNonIndexedKeysList(List<SectionKey> nonIndexedKeysList) {
        this.nonIndexedKeysList = nonIndexedKeysList;
    }

    public Map<String, SectionKey> getIndexedKeysMap() {
        return indexedKeysMap;
    }

    public void setIndexedKeysMap(Map<String, SectionKey> indexedKeysMap) {
        this.indexedKeysMap = indexedKeysMap;
    }
    private void fillNonIndexedKeysMap() {
        this.nonIndexedKeysMap = new HashMap<>();
        for (int i = 0; i < this.nonIndexedKeysList.size() - 1; i++) {
            nonIndexedKeysMap.put(i + "", nonIndexedKeysList.get(i));
        }
        nonIndexedKeysMap.put(-1 + "", nonIndexedKeysList.get(nonIndexedKeysList.size() - 1));
    }

    private void fillIndexedKeysMap() {
        this.indexedKeysMap = new HashMap<>();
        for (int i = 0; i < this.indexedKeysList.size()/* - 1*/; i++) {
            indexedKeysMap.put(i + "", indexedKeysList.get(i));
        }
        //indexedKeysMap.put(-1 + "", indexedKeysList.get(indexedKeysList.size() - 1));
    }

    public void fillAllSectionKeysLists() {
        var us0k0 = new SectionKey(
                "identyfikator rodzaju stacji",
                asList(new SubkeyDetails(4)));
        var us0k1 = new SectionKey(
                "czas dokonania obserwacji i wska??nik wiatru",
                asList(new SubkeyDetails(2), new SubkeyDetails(2), new SubkeyDetails(1, false)));
        var us0k2 = new SectionKey(
                "mi??dzynarodowy identyfikator stacji l??dowej",
                asList(new SubkeyDetails(2), new SubkeyDetails(3)));
        var us1k0 = new SectionKey(
                "wska??nik grupy opadowej, wysoko???? podstawy chmur i widzialno???? pozioma",
                asList(new SubkeyDetails(1, false), new SubkeyDetails(1, false),
                        new SubkeyDetails(1, false), new SubkeyDetails(2, false)));

        var us1k1 = new SectionKey(
                "wielko???? zachmurzenia og??lnego i dane o wietrze",
                asList(new SubkeyDetails(1, false), new SubkeyDetails(2, false)
                        , new SubkeyDetails(2)));
        var s1k0 = new SectionKey(
                "dodatkowa grupa uwzgl??dniana we wszystkich raportach synoptycznych, gdy pr??dko???? wiatru, w jednostkach wskazanych przez iw, wynosi 99 w??z????w lub wi??cej",
                asList(new SubkeyDetails(3)));
        var s1k1 = new SectionKey(
                "temperatura powietrza",
                asList(new SubkeyDetails(1), new SubkeyDetails(3)));
        var s1k2 = new SectionKey(
                "temperatura punktu rosy",
                asList(new SubkeyDetails(1), new SubkeyDetails(3)));
        var s1k3 = new SectionKey(
                "ci??nienie atmosferyczne na poziomie stacji",
                asList(new SubkeyDetails(4)));
        var s1k4 = new SectionKey(
                "ci??nienie atmosferyczne na poziomie morza",
                asList(new SubkeyDetails(4)));
        var s1k5 = new SectionKey(
                "tendencja ci??nienia atmosferycznego",
                asList(new SubkeyDetails(1, false), new SubkeyDetails(3)));
        var s1k6 = new SectionKey(
                "suma opadu",
                asList(new SubkeyDetails(3, true), new SubkeyDetails(1, false)));
        var s1k7 = new SectionKey(
                "stan pogody bie????cej i ubieg??ej",
                asList(new SubkeyDetails(2, false), new SubkeyDetails(2, false)));
        var s1k8 = new SectionKey(
                "chmury",
                asList(new SubkeyDetails(1), new SubkeyDetails(1, false), new SubkeyDetails(1, false)
                        , new SubkeyDetails(1, false)));
        var s1k9 = new SectionKey(
                "aktualny czas obserwacji, gdy rzeczywisty czas obserwacji r????ni si?? o wi??cej ni?? 10 minut od standardowego czasu GG podanego w sekcji 0",
                asList(new SubkeyDetails(2), new SubkeyDetails(2)));

        this.nonIndexedKeysList.addAll(Arrays.stream((new SectionKey[] {us0k1, us0k2, us1k0, us1k1})).toList());
        this.nonIndexedKeysList.add(us0k0);

        this.indexedKeysList.addAll(Arrays.stream((new SectionKey[] {s1k0, s1k1, s1k2, s1k3, s1k4, s1k5, s1k6, s1k7, s1k8, s1k9})).toList());
    }
    public static <T> List<T> asList(T... args) {
        return new ArrayList<>(List.of(args));
    }
}
