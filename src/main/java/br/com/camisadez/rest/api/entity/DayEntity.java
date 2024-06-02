/**
 * @(#)TaskEntity.java
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
 * Classe <code>TaskEntity</code>
 *
 * Essa classe entidade representa uma tarefa.
 * */
@Entity
@Table(name="tb_days")
@Data
public class DayEntity {
    /**
     * Identificadores
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="fk_template_parent", nullable = false)
    private TemplateEntity templateParent;

    @OneToMany(mappedBy="taskParent", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<TodoTaskEntity> todoChildrens;
    
    /**
     * Colunas.
     * */
    @Column(name="day_name", nullable = false)
    private String dayName;

    @Column(name="day_desc")
    @Lob
    private String dayDesc;

    /**
     * A data do dia é o dia na qual a tarefa será executada. o start date e o end date devem estar dentro dessa data.
     * */
    @Column(name="context_date", nullable = false)
    private LocalDate dateOfTheDay;

    @Column(name="dt_created_at", nullable = false)
    private LocalDate dtCreatedAt;

    @Column(name="tm_created_at", nullable = false)
    private LocalTime tmCreatedAt;

    @Column(name="dt_included_at")
    private LocalDate dtIncludedAt;

    @Column(name="tm_included_at")
    private LocalTime tmIncludedAt;

    /**
     * Construtor da classe <code>TaskEntity</code>.
     * */
    public DayEntity() {

    }

    public void prepareToCreate() {
        this.dtCreatedAt = LocalDate.now();
        this.tmCreatedAt = LocalTime.now();
        this.dtIncludedAt = LocalDate.now();
        this.tmIncludedAt = LocalTime.now();
    }

    public void prepareToUpdate() {
        this.dtIncludedAt = LocalDate.now();
        this.tmIncludedAt = LocalTime.now();
    }
}
