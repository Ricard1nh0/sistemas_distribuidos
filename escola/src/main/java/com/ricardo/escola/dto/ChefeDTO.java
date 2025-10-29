package com.ricardo.escola.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ChefeDTO {
    private UUID id;
    private String nome;
    private String email;
}