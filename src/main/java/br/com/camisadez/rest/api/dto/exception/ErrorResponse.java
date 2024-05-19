package br.com.camisadez.rest.api.dto.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private Integer metaType;
    private Error error;
    private String message;

    public ErrorResponse() {

    }
}
