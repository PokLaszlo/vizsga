package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    private ArrayList<Berles> berlesek = FileReader.readChefBerlesek2025CSV();

    public void getMonthIncome(){
        berlesek.sort((o1, o2)-> o1.getEndDate().compareTo(o2.getEndDate()));
        System.out.print("Adjon meg egy hónapot (1-12): ");
        Scanner sc = new Scanner(System.in);
        Integer month = sc.nextInt();
        Integer sum = 0;
        for (Berles berles : berlesek) {
            if (berles.getEndDate().getMonthValue() == month) {
                sum += berles.TotalPrice();
            }
        }
        System.out.println("A(z) " + month + ". hónap bevétele: " + sum+" euró");
        sc.close();
    }
    public void getAllIncome(){
        Integer sum = 0;
        for (Berles berles : berlesek) {
            sum += berles.TotalPrice();
        }
        System.out.println("A teljes 2025-es éves bevétel: " + sum+" euró");
    }
    public void getMostExpensiveRent(){
        berlesek.sort((o1, o2)-> o2.TotalPrice() - o1.TotalPrice());
        Integer cost = berlesek.get(0).TotalPrice();
        String chef = berlesek.get(0).getName();
        System.out.println("A legdrágább bérlés " + chef +" séftől volt, teljes ár: " + cost+" euró");
    }
    public void getRentedChevesNum(){
        ArrayList<Integer> chefs = new ArrayList<Integer>();
        for (Berles berles : berlesek) {
            if (!chefs.contains(berles.getChefId())) {
                chefs.add(berles.getChefId());
            }           
        }
        System.out.println("Összesen "+chefs.size()+" különböző séfet béreltek ki.");
    }
    public void getMostRentedChef(){
        HashMap <String, Integer> chefs = new HashMap<String, Integer>();
        for (Berles berles : berlesek) {
            String chef = berles.getName();
            chefs.merge(chef, 1, Integer::sum);
        }
        System.out.println("A legtöbbször bérelt séf: ");
        for(Map.Entry<String, Integer> entry : chefs.entrySet()) {
            if (entry.getValue() == chefs.values().stream().max(Integer::compareTo).get()) {
                String res="\t"+entry.getKey() +" ("+entry.getValue()+" bérlés)";
                System.out.println(res);
            }
        }
    }
    public void getRentsGroupedByCoisines(){
        HashMap <String, Integer> cuisines = new HashMap<String, Integer>();
        for (Berles berles : berlesek) {
            String cuisine = berles.getCuisine();
            cuisines.merge(cuisine, 1, Integer::sum);
        }
        System.out.println("Bérlések száma konyhatípusonként:");
        for(Map.Entry<String, Integer> entry : cuisines.entrySet()) {
            String res="\t"+entry.getKey() +": "+entry.getValue()+" bérlés";
            System.out.println(res);
        }
    }
    public void getAvgRentTime(){
        Long sum = 0L;
        for (Berles berles : berlesek) {
            Long days =berles.getEndDate().toEpochDay() - berles.getStartDate().toEpochDay();
            sum += days;
        }
        System.out.printf("Átlagos bérlési időtartam: %.2f nap", (double)sum/berlesek.size());
    }
}
