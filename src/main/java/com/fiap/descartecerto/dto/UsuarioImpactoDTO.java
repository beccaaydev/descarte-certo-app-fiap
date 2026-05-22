package com.fiap.descartecerto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioImpactoDTO {
    private Long idUsuario;
    private String nome;
    private BigDecimal totalKgReciclado;
    private BigDecimal totalCo2Evitado;
}