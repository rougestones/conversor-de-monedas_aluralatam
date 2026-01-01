package com.CurrencyConverter.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record CurrencyCodesRecord(List<List<String>> supported_codes) {

    public Map<String, String> asMap() {
        Map<String, String> map = new HashMap<>();
        for (List<String> par : supported_codes) {
            map.put(par.get(0), par.get(1));
        }
        return map;
    }

}