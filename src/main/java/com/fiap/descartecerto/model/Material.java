package com.fiap.descartecerto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_MATERIAIS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Long idMaterial;
    
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    
    @Column(name = "categoria", length = 50)
    private String categoria;
    
    @Column(name = "risco_ambiental")
    private String riscoAmbiental;
    
    @Column(name = "como_descartar")
    private String comoDescartar;
    
    @Column(name = "is_perigoso")
    private Integer isPerigoso;
    
    @Column(name = "is_reciclavel")
    private Integer isReciclavel;
    
    @Column(name = "data_criacao", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;
}

