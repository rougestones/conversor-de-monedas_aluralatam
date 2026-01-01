package com.CurrencyConverter.model;

import com.CurrencyConverter.service.Conversion;
import com.google.gson.*;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ExchangeRateApiClient {
    private final Properties prop = this.getProperties();
    private final String API_KEY = prop.getProperty("API_KEY");
    private final HttpClient client;
    private final Gson gson;

    public ExchangeRateApiClient() {
        client = HttpClient.newHttpClient();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    private Properties getProperties() {
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
        return prop;
    }

    public Conversion conversion(Currency currency, Currency otherCurrency, double amount) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss");
        URI adress = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + currency.getCode() + "/" + otherCurrency.getCode() + "/" + amount);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .GET()
                .build();
        try {
            HttpResponse<String> response;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement element = JsonParser.parseString(response.body());
            JsonObject object = element.getAsJsonObject();
            double conversionResult = object.get("conversion_result").getAsDouble();
            String conversionDate = dateTime.format(formatTime);
            return new Conversion(currency, amount, otherCurrency, conversionResult, conversionDate);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    public void supportedCurrencyCodes() {
        URI adress = URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/codes");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .GET()
                .build();


        try {
            HttpResponse<String> response;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement element = JsonParser.parseString(response.body());
            JsonObject object = element.getAsJsonObject();

            FileWriter writer = new FileWriter("supportedCurrencyCodes.json");
            writer.write(gson.toJson(object));
            writer.close();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.getStackTrace();
        }
    }
    //    Returns the exchange Rate between two currencies
    public double getExchangeRate(String currency, String otherCurrency) {
        URI adress = URI.create("https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/"+currency+"/"+otherCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(adress)
                .GET()
                .build();

        try {
            HttpResponse<String> response;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            RecordCurrency monedaRecord = gson.fromJson(response.body(), RecordCurrency.class);
            return monedaRecord.conversion_rate();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error: "+ e.getMessage());
        }
    }
}