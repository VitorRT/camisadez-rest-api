package br.com.camisadez.rest.api.dto.todotask;

import br.com.camisadez.rest.api.dto.day.DayRelationShip;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoTaskPayloadByList {
    @NotNull(message = "A lista de a fazares é obrigatória")
    private List<TodoTaskPayload> todoList;
    @NotNull(message="O id do dia pai é obrigatório")
    private DayRelationShip dayParent;
}
