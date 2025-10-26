package com.ricardo.imc.service;

import com.ricardo.imc.dto.ImcResultDto;
import com.ricardo.imc.exception.DivisionByZeroLikeException;
import com.ricardo.imc.exception.InvalidHeightException;
import com.ricardo.imc.exception.InvalidWeightException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ImcService {

    private static final double MIN_ALTURA = 0.5;
    private static final double MAX_ALTURA = 2.5;
    private static final double MIN_PESO = 2.0;
    private static final double MAX_PESO = 400.0;

    public ImcResultDto calcularImc(double peso, double altura) {
        
        validarEntradas(peso, altura);

        double imcValue = peso / (altura * altura);

        String classificacao = getClassificacaoOms(imcValue);
        String faixa = getFaixaOms(classificacao);

        DecimalFormat df = new DecimalFormat("#.##");
        double imcArredondado = Double.parseDouble(df.format(imcValue).replace(",", "."));

        return new ImcResultDto(peso, altura, imcArredondado, classificacao, faixa);
    }

    private void validarEntradas(double peso, double altura) {
        if (altura <= 0) {
            throw new DivisionByZeroLikeException("Altura não pode ser zero ou negativa.");
        }
        if (altura < MIN_ALTURA || altura > MAX_ALTURA) {
            throw new InvalidHeightException("Altura inválida. Deve estar entre " + MIN_ALTURA + "m e " + MAX_ALTURA + "m.");
        }

        if (peso < MIN_PESO || peso > MAX_PESO) {
            throw new InvalidWeightException("Peso inválido. Deve estar entre " + MIN_PESO + "kg e " + MAX_PESO + "kg.");
        }
    }

    private String getClassificacaoOms(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 24.9) return "Peso normal";
        if (imc < 29.9) return "Sobrepeso";
        if (imc < 34.9) return "Obesidade Grau I";
        if (imc < 39.9) return "Obesidade Grau II";
        return "Obesidade Grau III (Mórbida)";
    }

    private String getFaixaOms(String classificacao) {
        switch (classificacao) {
            case "Abaixo do peso": return "< 18.5";
            case "Peso normal": return "18.5 - 24.9";
            case "Sobrepeso": return "25.0 - 29.9";
            case "Obesidade Grau I": return "30.0 - 34.9";
            case "Obesidade Grau II": return "35.0 - 39.9";
            case "Obesidade Grau III (Mórbida)": return ">= 40.0";
            default: return "N/A";
        }
    }
}