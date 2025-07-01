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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/points")
@Transactional
public class PointController {

    @Autowired
    PointRepository pointRepository;

    // 특정 User의 현재 Point를 조회하는 DTO (Read Model)
    @GetMapping("/{userId}")
    public GetAllPointQuery getPointByUser(@PathVariable Long userId) {
        // 특정 userId가 가지고 있는 point를 가져옴.
        Point point = pointRepository.findByUserId(userId);

        if (point == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "포인트 정보 없음");
        }

        // Aggregate → DTO 변환
        GetAllPointQuery result = new GetAllPointQuery();
        result.setId(point.getId());
        result.setPoint(point.getPoint());
        result.setUserId(point.getUserId());
        return result;
    }

    // 포인트 구매 Command
    @PostMapping("/buy")
    public void buyPoint(@RequestHeader("X-USER-ID") Long userId,
                         @RequestBody BuyPointCommand cmd) { // HTTP Header로 UserId 받는 방식으로 구현

        Point point = pointRepository.findByUserId(userId);

        if (point == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "포인트 정보 없음");
        }

        point.buyPoint(cmd);
        pointRepository.save(point);
    }
}
//>>> Clean Arch / Inbound Adaptor
