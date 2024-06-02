/**
 * @(#)TodoTaskEntity.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Classe <code>TodoTaskEntity</code>
 *
 * Essa classe entidade representa uma sub-tarefa de uma tarefa. São os a fazeres de uma tarefa.
 * */
@Entity
@Table(name="tb_todo_tasks")
@Data
public class TodoTaskEntity {
	/**
	 * Identificadores.
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="fk_day_parent", nullable=false)
	private DayEntity dayParent;
	
	/**
	 * Colunas.
	 * */
	@Column(name="todo_name", nullable=false)
	private String todoName;
	
	@Column(name="has_been_completed", nullable=false)
	private Boolean hasBeenCompleted;
	
	@Column(name="todo_color")
	private String todoColor;
	
	@Column(name="todo_desc")
	private String todoDesc;
		
	/**
	 * Datas.
	 * */
	@Column(name="start_hour", nullable=false)
	private LocalTime start;
	
	@Column(name="end_hour", nullable=false)
	private LocalTime end;
	
	@Column(name="dt_created_at", nullable=false)
	private LocalDate dtCreatedAt;
	
	@Column(name="tm_created_at", nullable=false)
	private LocalTime tmCreatedAt;
	
	@Column(name="dt_included_at", nullable=false)
	private LocalDate dtIncludedAt;
	
	@Column(name="tm_included_at", nullable=false)
	private LocalTime tmIncludedAt;
	
	/**
	 * Construtor da classe <code>TodoTaskEntity</code>.
	 * */
	public TodoTaskEntity() {
		super();
	}

	public void prepareToCreate() {
		this.dtCreatedAt = LocalDate.now();
		this.tmCreatedAt = LocalTime.now();
		this.dtIncludedAt = LocalDate.now();
		this.tmIncludedAt = LocalTime.now();
		this.todoColor = "#C7C7C7";
		this.hasBeenCompleted = false;
	}

	public void prepareToUpdate() {
		this.dtIncludedAt = LocalDate.now();
		this.tmIncludedAt = LocalTime.now();
	}
}
