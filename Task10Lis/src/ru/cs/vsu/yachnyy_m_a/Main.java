package ru.cs.vsu.yachnyy_m_a;

import com.google.gson.Gson;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        StringBuilder json_b = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            while (scanner.hasNext()){
                json_b.append(scanner.nextLine());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        String json = json_b.toString();
        Flat[] flats = gson.fromJson(json, Flat[].class);
        for(Flat flat: flats){
            System.out.println(flat.district);
            System.out.println(flat.price);
            System.out.println(flat.area);
        }
    }
}
