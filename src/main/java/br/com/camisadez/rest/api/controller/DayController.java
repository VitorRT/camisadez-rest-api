/**
 * @(#)TaskController.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.controller;

import br.com.camisadez.rest.api.dto.response.GenericResponse;
import br.com.camisadez.rest.api.dto.day.DayPayload;
import br.com.camisadez.rest.api.service.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Classe <code>{@link DayController}</code>.
 *
 * Essa classe controladora é responsável por receber requisições HTTP para o recurso de tasks.
 * */
@RestController
@RequestMapping("/day")
public class DayController {
    /**
     * Dependências.
     * */
    private DayService dayService;

    /**
     * Construtor da classe <code>DayController</code> para injeção de dependências.
     * */
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> doCreateDay(@RequestBody @Validated DayPayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(dayService.doCreateDay(payload));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{dayId}")
    public ResponseEntity<GenericResponse> doUpdateDay(@PathVariable UUID dayId, @RequestBody @Validated DayPayload payload) {
        GenericResponse response = new GenericResponse();
        response.setData(dayService.doUpdateDay(dayId, payload));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse> getAllMyDays(@RequestParam(name="templateParentID", required=true) UUID templateId) {
        GenericResponse response = new GenericResponse();
        response.setData(dayService.getAllMyDays(templateId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/show/{dayId}")
    public ResponseEntity<GenericResponse> getMyDays(@PathVariable UUID dayId) {
        GenericResponse response = new GenericResponse();
        response.setData(dayService.getMyDays(dayId));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{dayId}")
    public ResponseEntity<Void> doDeleteMyDay(@PathVariable UUID dayId) {
        dayService.doDeleteMyDay(dayId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
