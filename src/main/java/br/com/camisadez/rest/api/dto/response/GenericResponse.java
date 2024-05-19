package br.com.camisadez.rest.api.dto.response;

import lombok.Data;

@Data
public class GenericResponse {
    private Integer metaType;
    Object data;

    public GenericResponse() {
        this.metaType = 1;
    }
}
