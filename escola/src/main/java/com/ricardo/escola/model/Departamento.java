package com.ricardo.escola.model; 

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "departamentos")
public class Departamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_departamento")
    private UUID idDepartamento;
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "sigla", unique = true)
    private String sigla;
    
    @Column(name = "centro_unidade_academica")
    private String centroUnidadeAcademica;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chefe_professor_id")
    private Professor chefeProfessor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chefe_funcionario_id")
    private Funcionario chefeFuncionario;
    
    @Column(name = "email_contato")
    private String emailContato;
    
    @Column(name = "telefone_ramal")
    private String telefoneRamal;
    
    @Column(name = "localizacao")
    private String localizacao;
}