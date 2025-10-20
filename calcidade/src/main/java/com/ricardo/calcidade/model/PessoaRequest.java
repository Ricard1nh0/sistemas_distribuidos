package com.ricardo.calcidade.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PessoaRequest {

    private String nome;
    private String sobrenome; // Opcional
    
    @JsonFormat(pattern = "dd/MM/yyyy") // Formato esperado na entrada
    private LocalDate dataNascimento;

    public PessoaRequest() {
    }

    public PessoaRequest(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}