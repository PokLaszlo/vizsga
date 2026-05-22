package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<Berles> readChefBerlesek2025CSV() {
        try {
            return tryReadChefBerlesek2025CSV();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return new ArrayList<Berles>();
        }
    }

    private static ArrayList<Berles> tryReadChefBerlesek2025CSV() throws FileNotFoundException {
        File file = new File("chef_berlesek_2025.csv");
        ArrayList<Berles> berlesek = new ArrayList<Berles>();
        try (Scanner scn = new Scanner(file)) {
            scn.nextLine();
            while (scn.hasNextLine()) {
                String[] berles = scn.nextLine().split(",");
                if (!isValidDate(berles[2]) || !isValidDate(berles[3])) {
                    continue;
                } else {
                    berlesek.add(
                            new Berles(
                                    Integer.parseInt(berles[0]),
                                    Integer.parseInt(berles[1]),
                                    LocalDate.parse(berles[2]),
                                    LocalDate.parse(berles[3]),
                                    Integer.parseInt(berles[4]),
                                    berles[5],
                                    berles[6]));
                }
            }
        }
        return berlesek;
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
