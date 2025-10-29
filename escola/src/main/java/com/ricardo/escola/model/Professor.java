package com.ricardo.escola.model; 

import com.ricardo.escola.model.enums.RegimeTrabalho;
import com.ricardo.escola.model.enums.Titulacao;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "professores")
public class Professor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_professor")
    private UUID idProfessor;
    
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    
    @Column(name = "cpf_registro_funcional", unique = true)
    private String cpf;
    
    @Column(name = "email_institucional", unique = true)
    private String emailInstitucional;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "titulacao")
    private Titulacao titulacao;
    
    @Column(name = "area_de_atuacao")
    private String areaDeAtuacao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "regime_trabalho")
    private RegimeTrabalho regimeTrabalho;
}