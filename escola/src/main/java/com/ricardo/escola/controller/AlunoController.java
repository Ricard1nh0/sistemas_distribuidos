package com.ricardo.escola.controller; 

import com.ricardo.escola.model.Aluno;
import com.ricardo.escola.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable UUID id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno criar(@RequestBody @Valid Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable UUID id, @RequestBody @Valid Aluno alunoDetalhes) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNomeCompleto(alunoDetalhes.getNomeCompleto());
                    aluno.setDataNascimento(alunoDetalhes.getDataNascimento());
                    aluno.setCpf(alunoDetalhes.getCpf());
                    aluno.setMatricula(alunoDetalhes.getMatricula());
                    aluno.setEmailInstitucional(alunoDetalhes.getEmailInstitucional());
                    aluno.setTelefone(alunoDetalhes.getTelefone());
                    return ResponseEntity.ok(alunoRepository.save(aluno));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (!alunoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        alunoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}