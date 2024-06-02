package br.com.camisadez.rest.api.dto.day;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayRelationShipResponse {
    private UUID id;
    private String name;
}
