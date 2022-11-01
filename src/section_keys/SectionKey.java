package section_keys;

import decoders.SubkeyDetails;

import java.util.List;

public class SectionKey {
    private String name;
    private List<SubkeyDetails> subkeyDetails; // chars reserved and whether encoded value == decoded value in subkey;
    //private boolean trueValue = false; // when encoded value == decoded value

    public SectionKey(String name, List<SubkeyDetails> subkeyDetails) {
        this.name = name;
        this.subkeyDetails = subkeyDetails;
    }
//    public SectionKey(String name, List<Integer> charsPerInformation, boolean trueValue) {
//        this.name = name;
//        this.charsPerInformation = charsPerInformation;
//        this.trueValue = trueValue;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubkeyDetails> getSubkeyDetails() {
        return this.subkeyDetails;
    }

    public void setCharsPerInformation(List<Integer> charsPerInformation) {
        this.charsPerInformation = charsPerInformation;
    }


}
