/**
 * @(#)TemplatePayload.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.template;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Classe <code>TemplatePayload</code>.
 *
 * Essa classe representa um payload DTO de um template usado para cadastros e atualizações.
 */
@Data
public class TemplatePayload {
    @NotBlank(message = "O nome do template é obrigatório.")
    private String name;
    private String description;

    /**
     * Construtor da classe <code>TemplatePayload</code>.
     * */
    public TemplatePayload() {

    }
}
