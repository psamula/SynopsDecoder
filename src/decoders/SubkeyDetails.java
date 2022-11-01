package decoders;

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
}
