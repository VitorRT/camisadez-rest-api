/**
 * @(#)TaskRepository.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.repository;

import br.com.camisadez.rest.api.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Interface <code>TaskRepository</code>
 *
 * Essa interface é um repositório com todos os métodos de ação da entidade <code>TaskEntity</code>.
 * */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    @Query("SELECT Ts FROM TaskEntity Ts WHERE Ts.templateParent.createdBy = :createdBy")
    List<TaskEntity> getAllMyTasks(@Param("createdBy") String id);

    @Query("SELECT Ts FROM TaskEntity Ts WHERE Ts.templateParent.id = :templateId")
    List<TaskEntity> getAllMyTemplateTasks(@Param("templateId") UUID id);

}
