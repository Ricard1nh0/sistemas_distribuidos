package com.ricardo.escola.dto;

import com.ricardo.escola.model.Departamento;
import com.ricardo.escola.model.Funcionario;
import com.ricardo.escola.model.Professor;
import lombok.Data;
import java.util.UUID;

@Data
public class DepartamentoResponseDTO {

    private UUID idDepartamento;
    private String nome;
    private String sigla;
    private String centroUnidadeAcademica;
    private String emailContato;
    private String telefoneRamal;
    private ChefeDTO chefe; 

    public DepartamentoResponseDTO(Departamento entidade) {
        this.idDepartamento = entidade.getIdDepartamento();
        this.nome = entidade.getNome();
        this.sigla = entidade.getSigla();
        this.centroUnidadeAcademica = entidade.getCentroUnidadeAcademica();
        this.emailContato = entidade.getEmailContato();
        this.telefoneRamal = entidade.getTelefoneRamal();
        
        if (entidade.getChefeProfessor() != null) {
            Professor p = entidade.getChefeProfessor();
            this.chefe = new ChefeDTO();
            this.chefe.setId(p.getIdProfessor());
            this.chefe.setNome(p.getNomeCompleto());
            this.chefe.setEmail(p.getEmailInstitucional());
        } else if (entidade.getChefeFuncionario() != null) {
            Funcionario f = entidade.getChefeFuncionario();
            this.chefe = new ChefeDTO();
            this.chefe.setId(f.getIdFuncionario());
            this.chefe.setNome(f.getNomeCompleto());
            this.chefe.setEmail(f.getEmailInstitucional());
        }
    }
}