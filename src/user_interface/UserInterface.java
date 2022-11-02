package user_interface;

import dao.SynopsKeysProvider;
import domain.SynopsMeasurement;
import handlers.MainHandler;
import printers.SynopPrinter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner sc;
    private final SynopsKeysProvider synopsKeysProvider;
    private final MainHandler mainHandler;

    public UserInterface(MainHandler mainHandler, Scanner sc, SynopsKeysProvider synopsKeysProvider) {
        this.synopsKeysProvider = synopsKeysProvider;
        this.mainHandler = mainHandler;
        this.sc = sc;
    }
    public LocalDate choseDate(String choice) {
        var parts = choice.split("-");

        try {
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int dayOfMonth = Integer.parseInt(parts[2]);
            var chosenDate = LocalDate.of(year, month, dayOfMonth);
            return chosenDate;
        }
        catch(NumberFormatException nfe) {
            System.out.println("Invalid input");
            System.exit(-1);
        }
        return null;
    }
    public void run() {
        LocalDate chosenToDate;
        LocalDate chosenFromDate;

        System.out.println("Choose date: (format: year-month-dayOfMonth)");
        System.out.println("Since?");
        chosenFromDate = choseDate(sc.nextLine());
        System.out.println("Until?");
        chosenToDate = choseDate(sc.nextLine());

        List<SynopsMeasurement> synopsToDisplay = new ArrayList<>();
        List<SynopsMeasurement> allSynopses;
        try {
            allSynopses = this.mainHandler.collectSynopses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var syn : allSynopses) {
            if (syn.getCreated().isBefore(chosenToDate) && syn.getCreated().isAfter(chosenFromDate)) {
                synopsToDisplay.add(syn);
            }
        }
        System.out.println("Available synops measurements: ");
        int cntr = 0;
        for (var s : synopsToDisplay) {
            System.out.println("Synops nr: " + cntr + ", Date: " + s.getCreated());
            cntr++;
        }
        System.out.println("Chose the synop measurement by nr");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        }
        catch(NumberFormatException nfe) {
            System.out.println("Invalid input");
            System.exit(-1);
        }
        if (choice < 0 || choice >= synopsToDisplay.size()) {
            System.out.println("Invalid input");
            System.exit(-2);
        }
        SynopPrinter.printer(this.synopsKeysProvider, synopsToDisplay.get(choice));

    }
}
