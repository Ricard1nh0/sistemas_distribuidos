package com.ricardo.escola.controller;

import com.ricardo.escola.model.Departamento;
import com.ricardo.escola.repository.DepartamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable UUID id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Departamento criar(@RequestBody @Valid Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizar(@PathVariable UUID id, @RequestBody @Valid Departamento departamentoDetalhes) {
        return departamentoRepository.findById(id)
                .map(departamento -> {
                    departamento.setNome(departamentoDetalhes.getNome());
                    departamento.setSigla(departamentoDetalhes.getSigla());
                    departamento.setCentroUnidadeAcademica(departamentoDetalhes.getCentroUnidadeAcademica());
                    departamento.setEmailContato(departamentoDetalhes.getEmailContato());
                    departamento.setTelefoneRamal(departamentoDetalhes.getTelefoneRamal());
                    departamento.setLocalizacao(departamentoDetalhes.getLocalizacao());
                    
                    departamento.setChefeProfessor(departamentoDetalhes.getChefeProfessor());
                    departamento.setChefeFuncionario(departamentoDetalhes.getChefeFuncionario());
                    
                    Departamento atualizado = departamentoRepository.save(departamento);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (!departamentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        departamentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}