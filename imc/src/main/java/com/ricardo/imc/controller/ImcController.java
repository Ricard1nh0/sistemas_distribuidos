package com.ricardo.imc.controller; 

import com.ricardo.imc.dto.ImcResultDto;
import com.ricardo.imc.service.ImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* Para teste 
    http://localhost:8080/imc?peso=70&altura=1.75
    http://localhost:8080/imc?peso=50&altura=1.75
    http://localhost:8080/imc?peso=85&altura=1.75
    http://localhost:8080/imc?peso=100&altura=1.75
*/ 

@RestController
@RequestMapping("/imc")
@Validated
public class ImcController {

    @Autowired
    private ImcService imcService;

    @GetMapping
    public ResponseEntity<ImcResultDto> calcularImcCompleto(
            @RequestParam(name = "peso") double peso,
            @RequestParam(name = "altura") double altura) {
        
        ImcResultDto resultado = imcService.calcularImc(peso, altura);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/classificacao")
    public ResponseEntity<ImcResultDto> obterClassificacao(
            @RequestParam(name = "peso") double peso,
            @RequestParam(name = "altura") double altura) {
        
        ImcResultDto resultado = imcService.calcularImc(peso, altura);
        return ResponseEntity.ok(resultado);
    }
}