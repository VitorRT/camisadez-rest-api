/**
 * @(#)TemplateRepository.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os diretos reservados.
 * */

package br.com.camisadez.rest.api.repository;

import br.com.camisadez.rest.api.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface <code>TemplateRepository</code>
 *
 * Essa interface é um repositório com todos os métodos de ação da entidade <code>TemplateEntity</code>.
 * */
@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, UUID> {
    @Query("SELECT T FROM TemplateEntity T WHERE T.createdBy = :createdBy")
    List<TemplateEntity> getAllMyTemplates(@Param("createdBy") String id);

    @Query("SELECT T FROM TemplateEntity T INNER JOIN TaskEntity Ts ON Ts.templateParent.id = T.id WHERE Ts.id = :taskId")
    Optional<TemplateEntity> getTemplateByTaskId(@Param("taskId") UUID taskId);
}
