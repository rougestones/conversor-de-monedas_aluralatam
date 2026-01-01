package com.CurrencyConverter.service;
import com.CurrencyConverter.model.Currency;
import com.CurrencyConverter.util.InputHandler;

public class Menu {
    private CurrencyManager manager;
    private String codeA;
    private String codeB;
    private double amount;
    private int option;

    public Menu(){
        manager = new CurrencyManager();
    }

    public void mainMenu(){
        manager.getStarted();
        do{
            this.showOptions();
            option = InputHandler.readMainOption();
            switch (option){
                case 1:
                    convertCurrencyMenu();
                    break;
                case 2:
                    manager.showConversionsFile();
                    break;
                case 3:
                    manager.showSupportedCurrencies();
                    System.out.println(manager.SupportedCurrencyCodesMap().size());
                    break;
                case 4:
                    System.out.println("""
                            
                            Leaving the program..
                            Thanks :)""");
                default:
                    break;
            }
        }while (option!=4);
    }

    private void showOptions(){
        System.out.print("""
                ******************************************
                            Currency converter
          
                1) Exchange currencies.
                2) Show the conversions history.
                3) View supported currencies.
                4) Exit.
                
                Select an option""");
    }

    private void convertCurrencyMenu(){
        Conversion conversion;
        System.out.println("\n******************************************\n");
        System.out.print("""
                Enter 1 to return to the main menu or
                Search currency code
                """);
        codeA = InputHandler.readCurrency(manager);
        if(codeA.equals("1")){
            System.out.println("Cancelling...\n");
        mainMenu();
        }

        System.out.print("Search another currency code");
        codeB = InputHandler.readCurrency(manager);
        if (codeB.equals("1")) mainMenu();

        if(codeA.equals(codeB)){
            System.out.println("Currencies must be different!");
            convertCurrencyMenu();
        }

        Currency currencyA = new Currency(codeA, manager.getSupportedCurrencyCodes().get(codeA));
        Currency currencyB = new Currency(codeB, manager.getSupportedCurrencyCodes().get(codeB));

        System.out.printf("Amount of money that you want to convert from [%s] to [%s]",currencyA.getCode(),currencyB.getCode());
        amount = InputHandler.readAmount();

        conversion = manager.convertCurrencies(currencyA, currencyB, amount);
        manager.addConversion(conversion);
        System.out.println("Result: ");
        System.out.print(conversion);
        manager.saveConversion();
        System.out.println();
        mainMenu();
    }
}