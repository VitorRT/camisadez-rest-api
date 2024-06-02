/**
 * @(#)TodoTaskRepository.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os diretos reservados.
 * */

package br.com.camisadez.rest.api.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.camisadez.rest.api.entity.TodoTaskEntity;

/**
 * Interface <code>TodoTaskRepository</code>
 *
 * Essa interface é um repositório com todos os métodos de ação da entidade <code>TodoTaskEntity</code>.
 * */
@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTaskEntity, UUID>{
    @Query("SELECT t FROM TodoTaskEntity t WHERE t.start < :end AND t.end > :start")
    List<TodoTaskEntity> findOverlappingTasks(@Param(value="start") LocalTime start, @Param(value="end") LocalTime end);

    @Query("SELECT td FROM TodoTaskEntity td INNER JOIN DayEntity t ON t.id = td.dayParent.id WHERE t.id = :dayId")
    List<TodoTaskEntity> getAllTasksTodoTasks(@Param(value="dayId") UUID dayId);
}
