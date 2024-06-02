package br.com.camisadez.rest.api.dto.todotask;

import br.com.camisadez.rest.api.dto.day.DayRelationShip;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TodoTaskPayload {
    @NotBlank(message="O nome do a fazer é obrigatório.")
    private String todoName;
    private String todoColor;
    private String todoDesc;
    @NotNull(message="O horário de início do a fazer é obrigatório.")
    private LocalTime start;
    @NotNull(message="O horário de término do a fazer é obrigatório.")
    private LocalTime end;
    @NotNull(message="O id do dia pai é obrigatório")
    private DayRelationShip dayParent;

    public TodoTaskPayload() {
        super();
    }
}
