package com.ricardo.escola.controller;

import com.ricardo.escola.model.Professor;
import com.ricardo.escola.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable UUID id) {
        return professorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Professor criar(@RequestBody @Valid Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable UUID id, @RequestBody @Valid Professor professorDetalhes) {
        return professorRepository.findById(id)
                .map(professor -> {
                    professor.setNomeCompleto(professorDetalhes.getNomeCompleto());
                    professor.setCpf(professorDetalhes.getCpf());
                    professor.setEmailInstitucional(professorDetalhes.getEmailInstitucional());
                    professor.setTelefone(professorDetalhes.getTelefone());
                    professor.setTitulacao(professorDetalhes.getTitulacao());
                    professor.setAreaDeAtuacao(professorDetalhes.getAreaDeAtuacao());
                    professor.setRegimeTrabalho(professorDetalhes.getRegimeTrabalho());
                    
                    Professor atualizado = professorRepository.save(professor);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (!professorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        professorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}