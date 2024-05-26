/**
 * @(#)TemplateService.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.service;

import br.com.camisadez.rest.api.dto.template.TemplateFullResponse;
import br.com.camisadez.rest.api.dto.template.TemplatePayload;
import br.com.camisadez.rest.api.dto.template.TemplateSimpleResponse;
import br.com.camisadez.rest.api.entity.TemplateEntity;
import br.com.camisadez.rest.api.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe <code>TemplateService</code>
 *
 * Essa classe concentra todos os serviços relacionados ao recurso de template.
 * */
@Service
public class TemplateService {
    /**
     * Dependências
     * */
    private TemplateRepository templateRepository;

    /**
     * Construtor da classe <code>TemplateService</code> para injeção de dependência.
     * */
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    /**
     * Serviços.
     * */

    /**
     * Esse método irá criar um novo template no sistema.
     * @param payload
     * @returns template
     * */
    public TemplateFullResponse doCreateTemplate(TemplatePayload payload) {
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setName(payload.getName());
        templateEntity.setDescription(payload.getDescription());
        templateEntity.prepareToCreate();

        TemplateEntity persisted = templateRepository.saveAndFlush(templateEntity);

        return new TemplateFullResponse(persisted);
    }

    /**
     * Esse método irá atualizar um template existente no sistema.
     * @param payload
     * @param id
     * @returns template
     * */
    public TemplateFullResponse doUpdateTemplate(TemplatePayload payload, UUID id) {
        TemplateEntity templateEntity = getTemplateById(id);
        templateEntity.setName(payload.getName());
        templateEntity.setDescription(payload.getDescription());
        templateEntity.prepareToUpdate();

        TemplateEntity persisted = templateRepository.saveAndFlush(templateEntity);

        return new TemplateFullResponse(persisted);
    }

    /**
     * Esse método irá retornar todos os templates existentes de um usuário no sistema.
     * @param createdBy
     * @returns templates
     * */
    public List<TemplateSimpleResponse> getAllMyTemplates(String createdBy) {
        return templateRepository.getAllMyTemplates(createdBy).stream().map(TemplateSimpleResponse::new).toList();
    }

    /**
     * Esse método irá retornar um template específico existente de um usuário no sistema.
     * @param id
     * @returns template
     * */
    public TemplateFullResponse getMyTemplate(UUID id) {
        TemplateEntity templateFounded = getTemplateById(id);
        return new TemplateFullResponse(templateFounded);
    }

    /**
     * Esse método irá deletar um template específico existente de um usuário no sistema.
     * @param id
     * @returns template
     * */
    public void doDeleteMyTemplate(UUID id) {
        TemplateEntity templateFounded = getTemplateById(id);
        templateRepository.delete(templateFounded);
    }

    /**
     * Utils methods.
     * */

    /**
     * Retorna um template pelo id.
     * Esse método lança exceções de <code>{@link jakarta.persistence.EntityNotFoundException}</code>.
     *
     * @param id
     * @return template
     * */
    private TemplateEntity getTemplateById(UUID id) {
        Optional<TemplateEntity> op =  templateRepository.findById(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Nenhum template foi encontrado com esse id.");
        return op.get();
    }
}
