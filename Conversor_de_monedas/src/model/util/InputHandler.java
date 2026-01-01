package com.CurrencyConverter.util;

import com.CurrencyConverter.service.CurrencyManager;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner keyboard = new Scanner(System.in);

    private static final int MIN_OPTION = 1;
    private static final int MAX_OPTION = 4;

    public static int readMainOption(){
        System.out.print(":");
        try {
            String input = keyboard.nextLine().trim();
            int temp = Integer.parseInt(input);
            if(temp>=MIN_OPTION && temp<=MAX_OPTION){
                return temp;
            }else{
                System.out.println("Choose a valid option!");
            }
        }catch (NumberFormatException e){
            System.out.print("Check what you're typing!\n");
            System.out.print(":");
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return readMainOption();
    }

    public static String readCurrency(CurrencyManager manager){
        System.out.print(":");
        String input = keyboard.nextLine().trim().toUpperCase();
        if(input.equals("1") || (input.length()==3 && input.chars().allMatch(Character::isLetter) && manager.getSupportedCurrencyCodes().get(input)!=null)){
            return input;
        }else{
            System.out.println("Choose a supported currency! For example: USD, EUR, etc.");
            return readCurrency(manager);
        }
    }

    public static double readAmount(){
        System.out.print(":");
        var input = keyboard.nextLine().replace(',','.').trim();
        double money = 0;
        try {
            if(input.chars().anyMatch(Character::isLetter) || input.chars().anyMatch(Character::isAlphabetic)){
                System.out.println("Type a number!");
                return readAmount();
            }
            money = Double.parseDouble(input);

        } catch (NumberFormatException e) {
            System.out.println("ERROR: "+e.getMessage());
            e.getStackTrace();
        }
        return money;
    }
}