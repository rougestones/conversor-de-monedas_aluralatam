package com.CurrencyConverter.service;

import com.CurrencyConverter.model.Currency;

public class Conversion {
    private final Currency currencyA;
    private final double amountMoney;
    private final Currency currencyB;
    private final double conversionResult;
    private final String conversionDate;

    public Conversion(Currency currencyA, double amountMoney, Currency currencyB, double conversionResult, String conversionDate){
        this.currencyA = currencyA;
        this.amountMoney = amountMoney;
        this.currencyB = currencyB;
        this.conversionResult = conversionResult;
        this.conversionDate = conversionDate;
    }

    @Override
    public String toString() {
        return "=> "+conversionDate+" | "+currencyA.getCode()+" $"+amountMoney+" = "+currencyB.getCode()+" $"+conversionResult+
                        " | "+currencyA.getName()+" to "+currencyB.getName()+"\n";
    }
}