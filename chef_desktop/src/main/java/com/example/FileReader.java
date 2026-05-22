package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<Koltseg> readChefKoltsegek2025CSV() {
        try {
            return tryReadChefKoltsegek2025CSV();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    private static ArrayList<Koltseg> tryReadChefKoltsegek2025CSV() throws FileNotFoundException {
        File file = new File("chef_koltsegek_2025.csv");
        ArrayList<Koltseg> koltsegek = new ArrayList<>();
        try (Scanner scn = new Scanner(file)) {
            scn.nextLine();
            while (scn.hasNextLine()) {
                String[] data = scn.nextLine().split(";");
                if (!isValidDate(data[2])) {
                    continue;
                } else {
                    koltsegek.add(new Koltseg(
                            Integer.parseInt(data[0]),
                            data[1],
                            LocalDate.parse(data[2]),
                            data[3],
                            Integer.parseInt(data[4]),
                            data[5]));
                }
            }
        }
        return koltsegek;
    }

    private static Boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
