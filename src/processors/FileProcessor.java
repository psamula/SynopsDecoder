package processors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileProcessor {
    private File file;
    private Map<String, String> indexedCodeKeys;
    private Scanner sc;
    public FileProcessor(File file) throws FileNotFoundException {
        this.file = file;
        sc = new Scanner(file);
        this. = new ArrayList<>();
    }
    private void splitStrings(String line) {
        var codeKeys = line.split(" ");
        for (String key : codeKeys) {
            indexedCodeKeys.put()
        }
    }
}
