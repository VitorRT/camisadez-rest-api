package br.com.camisadez.rest.api.controller;

import br.com.camisadez.rest.api.dto.response.GenericResponse;
import br.com.camisadez.rest.api.dto.template.TemplateFullResponse;
import br.com.camisadez.rest.api.dto.template.TemplatePayload;
import br.com.camisadez.rest.api.dto.template.TemplateSimpleResponse;
import br.com.camisadez.rest.api.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/template")
public class TemplateController {
    private TemplateService service;

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
        response.setData(service.getAllMineTemplates(createdBy));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<GenericResponse> getOneTemplate( @PathVariable String id ) {
        GenericResponse response = new GenericResponse();
        response.setData(service.getOneTemplate(UUID.fromString(id)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOneTemplate( @PathVariable String id ) {
        service.deleteOneTemplate(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
