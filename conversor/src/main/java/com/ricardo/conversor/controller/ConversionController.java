package com.ricardo.conversor.controller;

import com.ricardo.conversor.dto.ConversionResult;
import com.ricardo.conversor.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/convert")
@Validated
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    /**
     * Endpoint para conversão de moedas.
     * GET /convert/currency?from=USD&to=BRL&amount=100
     */
    @GetMapping("/currency")
    public ResponseEntity<ConversionResult> convertCurrency(
            @RequestParam @NotBlank
            @Pattern(regexp = "USD|BRL|EUR", message = "Moeda 'from' não suportada. Use USD, BRL ou EUR.")
            String from,

            @RequestParam @NotBlank
            @Pattern(regexp = "USD|BRL|EUR", message = "Moeda 'to' não suportada. Use USD, BRL ou EUR.")
            String to,

            @RequestParam
            @Max(value = 1000000, message = "Valor (amount) excede o limite máximo de 1.000.000.")
            double amount
    ) {
        ConversionResult result = conversionService.convertCurrency(from, to, amount);
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint para conversão de unidades.
     * GET /convert/unit/temperature?from=C&to=F&value=37
     * GET /convert/unit/distance?from=km&to=mi&value=10
     */
    @GetMapping("/unit/{category}")
    public ResponseEntity<ConversionResult> convertUnit(
            @PathVariable
            @Pattern(regexp = "temperature|distance", message = "Categoria não suportada. Use 'temperature' ou 'distance'.")
            String category,

            @RequestParam @NotBlank(message = "Parâmetro 'from' é obrigatório.")
            String from,

            @RequestParam @NotBlank(message = "Parâmetro 'to' é obrigatório.")
            String to,

            @RequestParam
            @Max(value = 1000000, message = "Valor (value) excede o limite máximo de 1.000.000.")
            double value
    ) {
        ConversionResult result = conversionService.convertUnit(category, from, to, value);
        return ResponseEntity.ok(result);
    }
}