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
    ReadingRepository readingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfPoint'"
    )
    public void wheneverOutOfPoint_ReadFail(@Payload OutOfPoint outOfPoint) {
        OutOfPoint event = outOfPoint;
        System.out.println(
            "\n\n##### listener ReadFail : " + outOfPoint + "\n\n"
        );

        // Comments //
        //열람 실패 상태
        //

        // Sample Logic //
        Reading.readFail(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PointDecreased'"
    )
    public void wheneverPointDecreased_ReadSuccess(
        @Payload PointDecreased pointDecreased
    ) {
        PointDecreased event = pointDecreased;
        System.out.println(
            "\n\n##### listener ReadSuccess : " + pointDecreased + "\n\n"
        );

        // Sample Logic //
        Reading.readSuccess(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
