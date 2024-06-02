package br.com.camisadez.rest.api.exception;

import lombok.Getter;

@Getter
public class ActionNotPermittedException extends RuntimeException {
    private String message;

    public ActionNotPermittedException(String message) {
        super(message);
        this.message = message;
    }
}
