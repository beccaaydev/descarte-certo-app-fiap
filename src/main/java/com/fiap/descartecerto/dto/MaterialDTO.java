package com.fiap.descartecerto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {
    private Long idMaterial;
    private String nome;
    private String categoria;
    private String riscoAmbiental;
    private String comoDescartar;
    private Integer isPerigoso;
    private Integer isReciclavel;
}
