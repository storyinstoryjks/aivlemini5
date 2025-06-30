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
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublishPrepared'"
    )
    public void wheneverPublishPrepared_RegisterBook(
        @Payload PublishPrepared publishPrepared
    ) {
        PublishPrepared event = publishPrepared;
        System.out.println(
            "\n\n##### listener RegisterBook : " + publishPrepared + "\n\n"
        );

        // Comments //
        //출간된 도서 등록

        // Sample Logic //
        Book.registerBook(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadSucceed'"
    )
    public void wheneverReadSucceed_GrantBestseller(
        @Payload ReadSucceed readSucceed
    ) {
        ReadSucceed event = readSucceed;
        System.out.println(
            "\n\n##### listener GrantBestseller : " + readSucceed + "\n\n"
        );

        // Comments //
        //열람된 도서 조회수 증가
        // 해당 도서의 조회수가 5회 이상이면 베스트 셀러

        // Sample Logic //
        Book.grantBestseller(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadApplied'"
    )
    public void wheneverReadApplied_ReadReceive(
        @Payload ReadApplied readApplied
    ) {
        ReadApplied event = readApplied;
        System.out.println(
            "\n\n##### listener ReadReceive : " + readApplied + "\n\n"
        );

        // Sample Logic //
        Book.readReceive(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
