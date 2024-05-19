/**
 * @(#)ExceptionUtils.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.utils;

import br.com.camisadez.rest.api.constants.ErrorType;
import br.com.camisadez.rest.api.dto.exception.Error;
import br.com.camisadez.rest.api.dto.exception.ErrorResponse;

/**
 * Classe <code>ExceptionUtils</code>
 *
 * Esse classe concentra funcionalidades de utilidades relacionadas a erros do sistema.
 * */
public class ExceptionUtils {

    /**
     * Método estático que retorna um objeto <code>ErrorResponse</code>.
     * @param message
     * @param errorType
     * @returns response
     * */
    public static ErrorResponse getErrorResponse(String message, ErrorType errorType) {
        ErrorResponse response = new ErrorResponse();
        Error error = new Error();

        error.setStatus(errorType.getStatus());
        error.setType(errorType.getType());

        response.setMessage(message);
        response.setMetaType(0);
        response.setError(error);

        return response;
    }
}
