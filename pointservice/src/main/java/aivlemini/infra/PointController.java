package aivlemini.infra;

import aivlemini.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/points")
@Transactional
public class PointController {

    @Autowired
    PointRepository pointRepository;

    // 특정 User의 현재 Point를 조회하는 DTO (Read Model)
    // 하티오스가 제공하는 링크랑 주소가 겹치면 해당 함수가 우선순위가 높아서 실행됨.
    // 따라서, 주석처리
    // @GetMapping("/{userId}")
    // public GetAllPointQuery getPointByUser(@PathVariable Long userId) {
    //     // 특정 userId가 가지고 있는 point를 가져옴.
    //     Point point = pointRepository.findByUserId(userId);

    //     if (point == null) {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "포인트 정보 없음");
    //     }

    //     // Aggregate → DTO 변환
    //     GetAllPointQuery result = new GetAllPointQuery();
    //     result.setId(point.getId());
    //     result.setPoint(point.getPoint());
    //     result.setUserId(point.getUserId());
    //     return result;
    // }

    // 포인트 구매 Command
    @RequestMapping(
        value = "/points/{id}/buypoint",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Point buyPoint(
        @PathVariable(value = "id") Long id,
        @RequestBody BuyPointCommand buyPointCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /point/buyPoint  called #####");
        Optional<Point> optionalPoint = pointRepository.findById(id);

        optionalPoint.orElseThrow(() -> new Exception("No Entity Found"));
        Point point = optionalPoint.get();
        point.buyPoint(buyPointCommand); // 여기에서 point값 변경

        pointRepository.save(point);
        return point;
    }
}
//>>> Clean Arch / Inbound Adaptor
