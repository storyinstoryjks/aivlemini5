package aivlemini.infra;

import aivlemini.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ScriptHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Script>> {

    @Override
    public EntityModel<Script> process(EntityModel<Script> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/requestpublish")
                .withRel("requestpublish")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/savetemporaryscript"
                )
                .withRel("savetemporaryscript")
        );

        return model;
    }
}
