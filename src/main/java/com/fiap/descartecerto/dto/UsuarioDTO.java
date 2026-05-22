package com.fiap.descartecerto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String cep;
    private BigDecimal totalKgReciclado;
    private BigDecimal totalCo2Evitado;
}