package handlers;

import file_managers.KeyValueFileManager;
import decoders.SynopsDecoder;
import dao.SynopsKeysProvider;
import decoders.SynopsReader;
import domain.SynopsMeasurement;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainHandler {
    private SynopsDecoder synopsDecoder;
    private SynopsKeysProvider synopsKeysProvider;
    private SynopsReader synopsReader;
    private KeyValueFileManager keyValueFileManager;
    private final String urlString;
    public MainHandler(String urlString, SynopsKeysProvider synopsKeysProvider, SynopsReader synopsReader, KeyValueFileManager keyValueFileManager) {
        this.urlString = urlString;
        this.synopsKeysProvider = synopsKeysProvider;
        this.synopsReader = synopsReader;
        this.keyValueFileManager = keyValueFileManager;
    }

    public List<SynopsMeasurement> collectSynopses() throws IOException {
        List<List<String>> synopsTxtList = new ArrayList<>();
        String line;
        URL url = new URL(urlString);
        Scanner s = new Scanner(url.openStream());

        while(s.hasNextLine()) {
            line = s.nextLine();
            synopsTxtList.add(Arrays.stream(line.split(" ")).toList());
        }
        List<SynopsMeasurement> synopsMeasurementList = new ArrayList<>();

        var synopsKeysProvider = new SynopsKeysProvider();
        var keyValueFileManager = new KeyValueFileManager(synopsKeysProvider);
        var synopsReader = new SynopsReader(keyValueFileManager, synopsKeysProvider);

        for (List<String> syn : synopsTxtList) {
            var synopsDecoder = new SynopsDecoder(syn, new SynopsMeasurement(), synopsReader);
            synopsMeasurementList.add(synopsDecoder.getResult());
        }
        return synopsMeasurementList;
    }
}