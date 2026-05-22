package com.fiap.descartecerto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_AGENDAMENTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long idAgendamento;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;
    
    @ManyToOne
    @JoinColumn(name = "id_ecoponto", nullable = false)
    private Ecoponto ecoponto;
    
    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;
    
    @Column(name = "status", length = 20)
    private String status;
    
    @Column(name = "data_criacao", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;
}


