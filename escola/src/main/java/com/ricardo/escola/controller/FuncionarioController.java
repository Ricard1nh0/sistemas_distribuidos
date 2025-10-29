package com.ricardo.escola.controller;

import com.ricardo.escola.model.Funcionario;
import com.ricardo.escola.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable UUID id) {
        return funcionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario criar(@RequestBody @Valid Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable UUID id, @RequestBody @Valid Funcionario funcionarioDetalhes) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNomeCompleto(funcionarioDetalhes.getNomeCompleto());
                    funcionario.setCpf(funcionarioDetalhes.getCpf());
                    funcionario.setEmailInstitucional(funcionarioDetalhes.getEmailInstitucional());
                    funcionario.setTelefone(funcionarioDetalhes.getTelefone());
                    funcionario.setCargo(funcionarioDetalhes.getCargo());
                    funcionario.setTipoVinculo(funcionarioDetalhes.getTipoVinculo());
                    
                    Funcionario atualizado = funcionarioRepository.save(funcionario);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        funcionarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}