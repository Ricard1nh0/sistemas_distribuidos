package com.ricardo.escola.model; 

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "alunos")
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_aluno")
    private UUID idAluno;
    
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    @Column(name = "cpf_matricula_nacional", unique = true)
    private String cpf;
    
    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;
    
    @Column(name = "email_institucional", unique = true)
    private String emailInstitucional;
    
    @Column(name = "telefone")
    private String telefone;
}