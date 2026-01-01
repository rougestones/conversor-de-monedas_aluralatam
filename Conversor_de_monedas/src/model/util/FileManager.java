package com.CurrencyConverter.util;

import com.CurrencyConverter.service.Conversion;

import java.io.*;
import java.util.Stack;

public class FileManager {

    public static String readFile(File file){
        String recovered = "";
        try {
            FileReader reader = new FileReader(file);
            int bina = reader.read();

            while (bina != -1) {
                recovered = recovered.concat("" + (char) bina);
                bina = reader.read();
            }
            reader.close();
            return recovered;
        } catch (IOException e){
            try {
                FileWriter writer = new FileWriter("conversions.txt");
            } catch (IOException ex) {
                System.out.println("Error: "+ ex.getMessage());
                ex.getStackTrace();
            }
        }
        return recovered;
    }

    public static void writeConversionsFile(String recoveredFile, Stack<Conversion> conversions){
        String newText = "";
        while (!conversions.empty()) {
            newText = newText.concat(conversions.pop().toString()) + "\n";
        }

        try {
            FileWriter writer = new FileWriter("conversions.txt");
            writer.write(newText+recoveredFile);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
            e.getStackTrace();
        }
    }

}