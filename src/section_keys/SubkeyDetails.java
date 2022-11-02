package section_keys;

import java.util.List;

public class SubkeyDetails
{
    private Integer charsPerSubkey;
    private boolean directValue = true; // when encoded value == decoded value

    public SubkeyDetails(Integer charsPerSubkey) {
        this.charsPerSubkey = charsPerSubkey;
    }

    public SubkeyDetails(Integer charsPerSubkey, boolean directValue) {
        this.charsPerSubkey = charsPerSubkey;
        this.directValue = directValue;
    }

    public Integer getCharsPerSubkey() {
        return charsPerSubkey;
    }

    public void setCharsPerSubkey(Integer charsPerSubkey) {
        this.charsPerSubkey = charsPerSubkey;
    }

    public boolean isDirectValue() {
        return directValue;
    }

    public void setDirectValue(boolean directValue) {
        this.directValue = directValue;
    }
}
