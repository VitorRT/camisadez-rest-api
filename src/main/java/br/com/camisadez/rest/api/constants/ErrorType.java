/**
 * @(#)ExceptionUtils.java
 *
 * Copyright 2024, camisadez
 *
 * Todos os direitos reservados.
 * */

package br.com.camisadez.rest.api.constants;

/**
 * Enum <code>ErrorType</code>
 *
 * Esse enum representa um Tipo de erro, bastante usado nos tratamento de erros do sistema.
 * */
public enum ErrorType {
    BAD_REQUEST(400, "BAD REQUEST");

    private Integer status;
    private String type;

    /**
     * Construtor do enum <code>ErrorType</code>.
     * */
    ErrorType(Integer status, String type){
        this.status = status;
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public Integer getStatus() {
        return this.status;
    }
}
