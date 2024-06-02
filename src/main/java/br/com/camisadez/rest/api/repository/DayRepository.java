/**
 * @(#)TaskRepository.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.repository;

import br.com.camisadez.rest.api.entity.DayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface <code>DayRepository</code>
 *
 * Essa interface é um repositório com todos os métodos de ação da entidade <code>DayEntity</code>.
 * */
@Repository
public interface DayRepository extends JpaRepository<DayEntity, UUID> {
    @Query("SELECT Ts FROM DayEntity Ts WHERE Ts.templateParent.createdBy = :createdBy")
    List<DayEntity> getAllMyDays(@Param("createdBy") String id);

    @Query("SELECT Ts FROM DayEntity Ts WHERE Ts.templateParent.id = :templateId")
    List<DayEntity> getAllMyTemplateDays(@Param("templateId") UUID id);

    @Query("SELECT t FROM DayEntity t INNER JOIN TodoTaskEntity td ON td.dayParent.id = t.id WHERE td.id = :todoId")
    Optional<DayEntity> getTaskByTodoDayId(@Param(value="todoId") UUID todoId);
}
