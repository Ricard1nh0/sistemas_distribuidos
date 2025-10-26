package com.ricardo.conversor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConversionResult {
    private String from;
    private String to;
    private Double originalValue;
    private Double convertedValue;
    private String unit;
}