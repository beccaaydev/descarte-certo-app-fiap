package com.fiap.descartecerto.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    private Long idAgendamento;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Long idUsuario;

    private String nomeUsuario;

    @NotNull(message = "O ID do material é obrigatório.")
    private Long idMaterial;

    private String nomeMaterial;

    @NotNull(message = "O ID do ecoponto é obrigatório.")
    private Long idEcoponto;

    private String nomeEcoponto;

    @NotNull(message = "A data da coleta é obrigatória.")
    @Future(message = "A data da coleta deve ser uma data futura.")
    private LocalDateTime dataColeta;

    private String status;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
