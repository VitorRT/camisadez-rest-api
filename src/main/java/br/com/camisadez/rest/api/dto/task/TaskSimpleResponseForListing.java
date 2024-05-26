package br.com.camisadez.rest.api.dto.task;

import br.com.camisadez.rest.api.dto.template.TemplateRelationShipResponse;
import br.com.camisadez.rest.api.entity.TemplateEntity;
import lombok.Data;

import java.util.List;

@Data
public class TaskSimpleResponseForListing {
    private TemplateRelationShipResponse templateParent;
    private List<TaskSimpleResponse> tasks;

    public TaskSimpleResponseForListing(TemplateEntity templateEntity) {
        this.templateParent = new TemplateRelationShipResponse(templateEntity.getId(), templateEntity.getName());
    }
}
