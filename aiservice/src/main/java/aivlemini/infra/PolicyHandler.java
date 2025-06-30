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
    PublishingRepository publishingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublicationRequested'"
    )
    public void wheneverPublicationRequested_Publish(
        @Payload PublicationRequested publicationRequested
    ) {
        PublicationRequested event = publicationRequested;
        System.out.println(
            "\n\n##### listener Publish : " + publicationRequested + "\n\n"
        );

        // Comments //
        //- AI 표지 이미시 생성
        // - 책 요약
        // - 책 구독료 선정?
        //

        // Sample Logic //
        Publishing.publish(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
