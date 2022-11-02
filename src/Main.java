import file_managers.KeyValueFileManager;
import dao.SynopsKeysProvider;
import decoders.SynopsReader;
import handlers.MainHandler;
import user_interface.UserInterface;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        var synopsKeysProvider = new SynopsKeysProvider();
        var keyValueFileManager = new KeyValueFileManager(synopsKeysProvider);
        var synopsReader = new SynopsReader(keyValueFileManager, synopsKeysProvider);
        var mainHandler = new MainHandler("https://www.ogimet.com/cgi-bin/getsynop?begin=202207270600&end=202207271200&state=Pol",
                synopsKeysProvider, synopsReader, keyValueFileManager);
        UserInterface ui = new UserInterface(mainHandler, new Scanner(System.in), synopsKeysProvider);
        ui.run();
    }
}