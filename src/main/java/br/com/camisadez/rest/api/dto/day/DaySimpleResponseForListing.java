package br.com.camisadez.rest.api.dto.day;

import br.com.camisadez.rest.api.dto.template.TemplateRelationShipResponse;
import br.com.camisadez.rest.api.entity.TemplateEntity;
import lombok.Data;

import java.util.List;

@Data
public class DaySimpleResponseForListing {
    private TemplateRelationShipResponse templateParent;
    private List<DaySimpleResponse> days;

    public DaySimpleResponseForListing(TemplateEntity templateEntity) {
        this.templateParent = new TemplateRelationShipResponse(templateEntity.getId(), templateEntity.getName());
    }
}
