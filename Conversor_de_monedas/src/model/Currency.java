package com.CurrencyConverter.model;

public class Currency {
    private final String code;
    private final String name;

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return String.format("(currencyCode: %s, currencyName: %s)",code, name );
    }
}