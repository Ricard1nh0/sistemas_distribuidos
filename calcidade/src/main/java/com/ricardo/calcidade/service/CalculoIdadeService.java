package com.ricardo.calcidade.service;

import com.ricardo.calcidade.model.IdadeResponse;
import com.ricardo.calcidade.model.PessoaRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CalculoIdadeService {

    public IdadeResponse calcularIdade(PessoaRequest pessoaRequest) {
        
        LocalDate dataNascimento = pessoaRequest.getDataNascimento();
        LocalDate hoje = LocalDate.now();

        if (dataNascimento.isAfter(hoje)) {
            throw new IllegalArgumentException("A data de nascimento não pode ser futura.");
        }

        Period idade = Period.between(dataNascimento, hoje);

        String nomeCompleto = pessoaRequest.getNome() + 
                              Optional.ofNullable(pessoaRequest.getSobrenome())
                                      .map(s -> " " + s)
                                      .orElse(""); 

        // 4. Retorno do objeto de resposta
        return new IdadeResponse(
            nomeCompleto,
            idade.getYears(),
            idade.getMonths(),
            idade.getDays()
        );
    }
}