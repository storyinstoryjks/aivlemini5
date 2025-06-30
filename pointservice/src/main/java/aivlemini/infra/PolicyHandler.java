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
    PointRepository pointRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserRegistered'"
    )
    public void wheneverUserRegistered_GainRegisterPoint(
        @Payload UserRegistered userRegistered
    ) {
        UserRegistered event = userRegistered;
        System.out.println(
            "\n\n##### listener GainRegisterPoint : " + userRegistered + "\n\n"
        );

        // Comments //
        //가입신청한 사용자가 KT면 5천포인트
        // 그렇지 않으면 1천포인트 지급

        // Sample Logic //
        Point.gainRegisterPoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReadReceived'"
    )
    public void wheneverReadReceived_DecreasePoint(
        @Payload ReadReceived readReceived
    ) {
        ReadReceived event = readReceived;
        System.out.println(
            "\n\n##### listener DecreasePoint : " + readReceived + "\n\n"
        );

        // Comments //
        //1. 요금제를 구매한 사용자인가?
        //  - (yes -> 포인트 0 차감)
        //  - (no -> 2)
        // 2. 잔여포인트 - 도서 가격 의 값이 0이상인가?
        //  - (yes -> 포인트차감됨 이벤트)
        //  - (no -> 포인트부족함 이벤트)

        // Sample Logic //
        Point.decreasePoint(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
