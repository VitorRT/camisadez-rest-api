/**
 * @(#)IncorrectlyFormattedDateException.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.exception;

import lombok.Getter;

/**
 * Classe de exceção <code>{@link IncorrectlyFormattedDateException}</code>.
 *
 * Essa classe é usada para lançar erros de formatação incorreta de datas no sistema.
 * */
@Getter
public class IncorrectlyFormattedDateException extends RuntimeException {
    private String message;

   /**
    * Construtor da classe <code>IncorrectlyFormattedDateException</code>.
    * */
    public IncorrectlyFormattedDateException(String message) {
        super(message);
        this.message = message;
    }
}
