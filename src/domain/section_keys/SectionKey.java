package domain.section_keys;

import java.util.List;

public class SectionKey {
    private String name;
    private List<SubkeyDetails> subkeyDetails; // amount of chars reserved and bool whether encoded value == decoded value in subkey;

    public SectionKey(String name, List<SubkeyDetails> subkeyDetails) {
        this.name = name;
        this.subkeyDetails = subkeyDetails;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubkeyDetails> getSubkeyDetails() {
        return this.subkeyDetails;
    }



}
