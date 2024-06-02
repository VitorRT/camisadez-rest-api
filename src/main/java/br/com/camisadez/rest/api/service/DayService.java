/**
 * @(#)TaskService.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.service;

import br.com.camisadez.rest.api.dto.day.DayFullResponse;
import br.com.camisadez.rest.api.dto.day.DayPayload;
import br.com.camisadez.rest.api.dto.day.DaySimpleResponse;
import br.com.camisadez.rest.api.dto.day.DaySimpleResponseForListing;
import br.com.camisadez.rest.api.entity.DayEntity;
import br.com.camisadez.rest.api.entity.TemplateEntity;
import br.com.camisadez.rest.api.repository.DayRepository;
import br.com.camisadez.rest.api.repository.TemplateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe <code>DayService</code>
 *
 * Essa classe concentra todos os serviços relacionados ao recurso de task.
 * */
@Service
public class DayService {
    /**
     * Dependências.
     * */
    private DayRepository dayRepository;
    private TemplateRepository templateRepository;

    /**
     * Construtor da classe <code>TaskService</code> para injeção de dependência.
     * */
    public DayService(DayRepository dayRepository, TemplateRepository templateRepository) {
        this.dayRepository = dayRepository;
        this.templateRepository = templateRepository;
    }

    /**
     * Serviços.
     * */

    /**
     * Esse método irá criar um novo dia no sistema.
     * @param payload
     * @return persisted
     * */
    public DayFullResponse doCreateDay(DayPayload payload) {
        TemplateEntity templateEntity = getTemplateById(UUID.fromString(payload.getTemplateParent().getId()));

        DayEntity dayEntity = new DayEntity();
        dayEntity.prepareToCreate();


        dayEntity.setDayName(payload.getDayName());
        dayEntity.setDayDesc(payload.getDayDesc());
        dayEntity.setDateOfTheDay(payload.getDateOfTheDay());
        dayEntity.setTemplateParent(templateEntity);

        DayEntity persisted = dayRepository.saveAndFlush(dayEntity);

        return new DayFullResponse(persisted, templateEntity);
    }

    /**
     * Esse método irá atualizar uma nova tarefa existente no sistema.
     * @param dayId
     * @param payload
     * @return
     * */
    public DayFullResponse doUpdateDay(UUID dayId, DayPayload payload) {
        TemplateEntity templateEntity = getTemplateById(UUID.fromString(payload.getTemplateParent().getId()));
        
        DayEntity dayEntity = getDayById(dayId);
        dayEntity.prepareToUpdate();

        dayEntity.setDayName(payload.getDayName());
        dayEntity.setDayDesc(payload.getDayDesc());
        dayEntity.setDateOfTheDay(payload.getDateOfTheDay());

        DayEntity persisted = dayRepository.saveAndFlush(dayEntity);

        return new DayFullResponse(persisted, templateEntity);
    }

    /**
     * Esse método irá retornar todos os dias existentes de um usuário no sistema.
     * @return days
     * */
    public DaySimpleResponseForListing getAllMyDays(UUID templateId) {
        List<DaySimpleResponse> days = dayRepository.getAllMyTemplateDays(templateId).stream().map(DaySimpleResponse::new).toList();
        TemplateEntity templateEntity = getTemplateById(templateId);
        DaySimpleResponseForListing data = new DaySimpleResponseForListing(templateEntity);
        data.setDays(days);
        return data;
    }

    /**
     * Esse método irá retornar uma task específica existente de um usuário no sistema.
     * @param dayId
     * @return day
     * */
    public DayFullResponse getMyDays(UUID dayId) {
        DayEntity dayEntity = getDayById(dayId);
        TemplateEntity templateEntity = getTemplateByDayId(dayId);
        return new DayFullResponse(dayEntity, templateEntity);
    }

    /**
     * Esse método irá deletar uma task específica existente de um usuário no sistema.
     * @param dayId
     * */
    public void doDeleteMyDay(UUID dayId) {
        DayEntity dayEntity = getDayById(dayId);
        dayRepository.delete(dayEntity);
    }


    /**
     * Utils methods.
     * */

    /**
     * Retorna um dia pelo 'id'.
     * Esse método lança exceções de <code>{@link jakarta.persistence.EntityNotFoundException}</code>.
     *
     * @param id
     * @return day
     * */
    private DayEntity getDayById(UUID id) {
        Optional<DayEntity> op =  dayRepository.findById(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Nenhum dia foi encontrado com esse id.");
        return op.get();
    }

    private TemplateEntity getTemplateById(UUID id) {
        Optional<TemplateEntity> op =  templateRepository.findById(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Nenhum template foi encontrado com esse id.");
        return op.get();
    }

    private TemplateEntity getTemplateByDayId(UUID id) {
        Optional<TemplateEntity> op =  templateRepository.getTemplateByDayId(id);
        if(op.isEmpty())
            throw new EntityNotFoundException("Esse template não possui nenhum dia cadastrado ou ele não existe.");
        return op.get();
    }
}
