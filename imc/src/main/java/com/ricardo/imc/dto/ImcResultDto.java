package com.ricardo.imc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class ImcResultDto {
    
    private double pesoFornecido; 
    private double alturaFornecida;
    private double imc;
    private String classificacao;
    private String faixaOms; 
    
}