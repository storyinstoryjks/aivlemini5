package aivlemini.domain;

import aivlemini.PointserviceApplication;
import aivlemini.domain.OutOfPoint;
import aivlemini.domain.PointBought;
import aivlemini.domain.PointDecreased;
import aivlemini.domain.RegisterPointGained;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Point_table")
@Data
// <<< DDD / Aggregate Root
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long point;

    private Long userId;

    public static PointRepository repository() {
        PointRepository pointRepository = PointserviceApplication.applicationContext.getBean(
                PointRepository.class);
        return pointRepository;
    }

    // <<< Clean Arch / Port Method
    public static void gainRegisterPoint(UserRegistered userRegistered) {
        // userName 유효성 검사
        if (userRegistered.getUserName() == null) {
            return;
        }

        // 포인트 객체 생성
        Point point = new Point();

        // 포인트 지급 로직
        if (userRegistered.getLoginId().toLowerCase().startsWith("kt-")) {
            point.setPoint(6000L); // userName이 "kt-" 인 경우 kt 고객으로 간주
        } else {
            point.setPoint(1000L); // kt 고객이 아닌 경우 기본 포인트 지급
        }

        // UserId 맵핑
        point.setUserId(userRegistered.getId());

        // point 객체 저장
        repository().save(point);

    }

    public static void decreasePoint(ReadReceived readReceived) {
        // UserId 로 포인트 조회
        Long userId = readReceived.getUserId(); // 도서 서비스에서 UserId를 ReadReceived Event에 추가해줘야 함
        Long readingId = readReceived.getReadingId(); // 도서 서비스에서 ReadingId를 ReadReceived Event에 추가해줘야 함
        Point point = repository().findByUserId(userId); // 열람 신청을 한 UserId 의 point 정보를 받아옴

        if (point == null) {
            System.out.println("포인트 정보 없음");
            return;
        }

        // 구독 여부 판단
        if (Boolean.TRUE.equals(readReceived.getIsPurchase())) {
            PointDecreased event = new PointDecreased(point); // 포인트 차감됨 Event 발행
            event.setReadingId(readingId); // 어떤 열람이 성공한건지 확인 위해 열람 Id 부여
            event.publishAfterCommit();
            return;
        }
        // 포인트 부족 여부 판단
        if (point.getPoint() < readReceived.getPrice()) {
            OutOfPoint event = new OutOfPoint(point); // 포인트 부족함 Event 발행
            event.setReadingId(readingId); // 어떤 열람이 성공한건지 확인 위해 열람 Id 부여
            event.publishAfterCommit();
            return; // 포인트 차감되지 않음.
        }

        // 포인트 차감
        point.setPoint(point.getPoint() - readReceived.getPrice());
        repository().save(point);

        // 포인트 차감 Event 발행
        PointDecreased event = new PointDecreased(point);
        event.setReadingId(readingId);
        event.publishAfterCommit();
    }

    //<<< Clean Arch / Port Method
    public void buyPoint(BuyPointCommand buyPointCommand) {
        this.setPoint(this.getPoint() + buyPointCommand.getPoint()); // 기존 포인트 값에 더하기

        PointBought pointBought = new PointBought(this);
        pointBought.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
}
