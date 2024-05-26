/**
 * @(#)TemplateRelationShip.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.template;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe <code>TemplateRelationShip</code>.
 *
 * Essa classe representa um objeto DTO usado como atributo de outros objetos para referenciar um relacionamento
 * com o recurso de templates.
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRelationShip {
    @NotBlank(message="o id do template pai é obrigatório.")
    private String id;
}
