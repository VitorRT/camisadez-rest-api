/**
 * @(#)Error.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.dto.exception;

import lombok.Data;

/**
 * Enum <code>Error</code>
 *
 * Esse enum representa um erro no sistema, usada em objetos DTO's de tratamentos e exceções.
 * */
@Data
public class Error {
    private String type;
    private Integer status;

    /**
     * Construtor da classe <code>Error</code>.
     * */
    public Error() {

    }
}
