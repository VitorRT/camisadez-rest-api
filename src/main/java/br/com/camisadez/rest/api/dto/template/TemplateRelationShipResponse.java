/**
 * @(#)TemplateRelationShipResponse.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Classe <code>TemplateRelationShipResponse</code>.
 *
 * Essa classe representa um objeto DTO usado como atributo de outros objetos em métodos de listagem
 * para referenciar um relacionamento com o recurso de templates.
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRelationShipResponse {
    private UUID id;
    private String name;
}
