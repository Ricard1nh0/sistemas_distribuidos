package com.ricardo.calcidade.controller;

import com.ricardo.calcidade.model.IdadeResponse;
import com.ricardo.calcidade.model.PessoaRequest;
import com.ricardo.calcidade.service.CalculoIdadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/idade")
public class IdadeController {

    @Autowired
    private CalculoIdadeService calculoIdadeService;

    @PostMapping("/calcular")
    public ResponseEntity<?> calcularIdade(@RequestBody PessoaRequest pessoaRequest) {
        
        try {
            IdadeResponse response = calculoIdadeService.calcularIdade(pessoaRequest);
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor: " + e.getMessage());
        }
    }
}