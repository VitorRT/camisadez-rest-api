/**
 * @(#)TemplateController.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.controller;

import br.com.camisadez.rest.api.dto.response.GenericResponse;
import br.com.camisadez.rest.api.dto.template.TemplatePayload;
import br.com.camisadez.rest.api.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe <code>{@link TemplateController}</code>.
 *
 * Essa classe controladora é responsável por receber requisições HTTP para o recurso de templates.
 * */
@RestController
@RequestMapping("/template")
public class TemplateController {
    /**
     * Dependências.
     * */
    private TemplateService service;

    /**
     * Construtor da classe <code>TemplateController</code> para injeção de dependência.
     * */
    public TemplateController(TemplateService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> doCreateTemplate(@RequestBody @Validated TemplatePayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(service.doCreateTemplate(payload));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse> doUpdateTemplate(@RequestBody @Validated TemplatePayload payload, @PathVariable String id) {
        GenericResponse response = new GenericResponse();
        response.setData(service.doUpdateTemplate(payload, UUID.fromString(id)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> getAllMineTemplates(@RequestParam(name = "createdBy", required = true) String createdBy) {
        GenericResponse response = new GenericResponse();
        response.setData(service.getAllMyTemplates(createdBy));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<GenericResponse> getOneTemplate( @PathVariable String id ) {
        GenericResponse response = new GenericResponse();
        response.setData(service.getMyTemplate(UUID.fromString(id)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOneTemplate( @PathVariable String id ) {
        service.doDeleteMyTemplate(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
