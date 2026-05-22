package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileWriting {
    public static void writeChefKoltsegek2025CSV(Koltseg koltseg) {
        try {
            tryWriteChefKoltsegek2025CSV(koltseg);
        } catch (IOException e) {
            System.err.println("Fájl írása nem sikerült!");
            e.printStackTrace();
        }
    }
    private static void tryWriteChefKoltsegek2025CSV(Koltseg koltseg) throws IOException {
        FileWriter fw = new FileWriter(
            "chef_koltsegek_2025.csv", 
            Charset.forName("UTF-8"), 
            true);
        String line = stringifyKoltseg(koltseg);
        fw.write(line);
        fw.close();
    }
    private static String stringifyKoltseg(Koltseg koltseg) {
        return koltseg.getId() + ";" + 
            koltseg.getChefname() + ";" + 
            koltseg.getDate() + ";" + 
            koltseg.getCategory() + ";" + 
            koltseg.getPrice() + ";" + 
            koltseg.getComment() + "\n";
    }
}
