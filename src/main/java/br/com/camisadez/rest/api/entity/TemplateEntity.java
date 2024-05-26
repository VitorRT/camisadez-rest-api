/**
 * @(#)TemplateEntity.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

/**
 * Classe <code>TemplateEntity</code>
 *
 * Essa classe entidade representa um template.
 * */
@Entity
@Table(name = "tb_templates")
@Data
public class TemplateEntity {
    /**
     * Identificadores
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy="templateParent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TaskEntity> tasks;

    /**
     * Colunas.
     * */
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, name = "created_by")
    private String createdBy;

    /**
     * Datas
     * */
    @Column(nullable = false, name = "dt_created_at")
    @Temporal(TemporalType.DATE)
    private LocalDate dtCreatedAt;

    @Column(nullable = false, name = "tm_created_at")
    @Temporal(TemporalType.TIME)
    private LocalTime tmCreatedAt;

    @Column(name = "dt_included_at")
    @Temporal(TemporalType.DATE)
    private LocalDate dtIncludedAt;

    @Column(name = "tm_included_at")
    @Temporal(TemporalType.TIME)
    private LocalTime tmIncludedAt;

    /**
     * Construtor da classe <code>TemplateEntity</code>.
     * */
    public TemplateEntity() {
        super();
    }

    /**
     * Métodos
     * */

    public void prepareToCreate() {
        this.dtCreatedAt = LocalDate.now();
        this.tmCreatedAt = LocalTime.now();
        this.dtIncludedAt = LocalDate.now();
        this.tmIncludedAt = LocalTime.now();
        this.status = "Inactive";
        this.createdBy = "vitor";
    }

    public void prepareToUpdate() {
        this.dtIncludedAt = LocalDate.now();
        this.tmIncludedAt = LocalTime.now();
    }
}
