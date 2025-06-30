package aivlemini.infra;

import aivlemini.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserInfoHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<UserInfo>> {

    @Override
    public EntityModel<UserInfo> process(EntityModel<UserInfo> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/buyplan")
                .withRel("buyplan")
        );

        return model;
    }
}
