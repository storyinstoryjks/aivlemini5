package aivlemini.infra;

import aivlemini.config.kafka.KafkaProcessor;
import aivlemini.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ScriptRepository scriptRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublishPrepared'"
    )
    public void wheneverPublishPrepared_StatusNotify(
        @Payload PublishPrepared publishPrepared
    ) {
        PublishPrepared event = publishPrepared;
        System.out.println(
            "\n\n##### listener StatusNotify : " + publishPrepared + "\n\n"
        );

        Script.statusNotify(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
