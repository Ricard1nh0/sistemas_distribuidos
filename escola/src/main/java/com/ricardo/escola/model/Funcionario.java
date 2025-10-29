package com.ricardo.escola.model; 

import com.ricardo.escola.model.enums.TipoVinculo;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_funcionario")
    private UUID idFuncionario;
    
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;
    
    @Column(name = "cpf_registro_funcional", unique = true)
    private String cpf;
    
    @Column(name = "email_institucional", unique = true)
    private String emailInstitucional;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "cargo")
    private String cargo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vinculo")
    private TipoVinculo tipoVinculo;
}