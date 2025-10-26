package com.ricardo.conversor.service;

import com.ricardo.conversor.dto.ConversionResult;
import com.ricardo.conversor.exception.ConversionRateNotFoundException;
import com.ricardo.conversor.exception.NegativeAmountNotAllowedException;
import com.ricardo.conversor.exception.UnsupportedUnitException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConversionService {

    private static final Map<String, Double> CURRENCY_RATES = Map.of(
            "USD_BRL", 5.39,
            "BRL_USD", 1 / 5.39,
            "USD_EUR", 0.86,
            "EUR_USD", 1 / 0.86,
            "BRL_EUR", 0.16,
            "EUR_BRL", 1 / 0.16
    );

    public ConversionResult convertCurrency(String from, String to, double amount) {
        if (amount < 0) {
            throw new NegativeAmountNotAllowedException("Valor (amount) não pode ser negativo.");
        }

        if (from.equalsIgnoreCase(to)) {
            return new ConversionResult(from, to, amount, amount, "currency");
        }

        String rateKey = from.toUpperCase() + "_" + to.toUpperCase();
        Double rate = CURRENCY_RATES.get(rateKey);

        if (rate == null) {
            throw new ConversionRateNotFoundException("Taxa de conversão não encontrada para " + from + " -> " + to);
        }

        double convertedValue = amount * rate;
        return new ConversionResult(from, to, amount, convertedValue, "currency");
    }

    public ConversionResult convertUnit(String category, String from, String to, double value) {
        switch (category.toLowerCase()) {
            case "temperature":
                return convertTemperature(from, to, value);
            case "distance":
                if (value < 0) {
                    throw new NegativeAmountNotAllowedException("Distância (value) não pode ser negativa.");
                }
                return convertDistance(from, to, value);
            default:
                throw new UnsupportedUnitException("Categoria de unidade '" + category + "' não suportada.");
        }
    }

    private ConversionResult convertTemperature(String from, String to, double value) {
        double convertedValue;
        String fromUpper = from.toUpperCase();
        String toUpper = to.toUpperCase();

        if (fromUpper.equals("C") && toUpper.equals("F")) {
            convertedValue = (value * 9.0 / 5.0) + 32.0;
        } else if (fromUpper.equals("F") && toUpper.equals("C")) {
            convertedValue = (value - 32.0) * 5.0 / 9.0;
        } else if (fromUpper.equals(toUpper)) {
            convertedValue = value; 
        } else {
            throw new UnsupportedUnitException("Conversão de temperatura não suportada: " + from + " -> " + to);
        }
        return new ConversionResult(from, to, value, convertedValue, "temperature");
    }

    private ConversionResult convertDistance(String from, String to, double value) {
        double convertedValue;
        String fromLower = from.toLowerCase();
        String toLower = to.toLowerCase();

        if (fromLower.equals("km") && toLower.equals("mi")) {
            convertedValue = value * 0.621371;
        } else if (fromLower.equals("mi") && toLower.equals("km")) {
            convertedValue = value / 0.621371;
        } else if (fromLower.equals(toLower)) {
            convertedValue = value;
        } else {
            throw new UnsupportedUnitException("Conversão de distância não suportada: " + from + " -> " + to);
        }
        return new ConversionResult(from, to, value, convertedValue, "distance");
    }
}