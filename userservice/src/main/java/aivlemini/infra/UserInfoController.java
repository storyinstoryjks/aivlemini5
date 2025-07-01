package aivlemini.infra;

import aivlemini.domain.*;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/userInfos")
@Transactional
public class UserInfoController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @GetMapping("/userapi")
    public List<UserInfo> getAllUsers() {
        return (List<UserInfo>) userInfoRepository.findAll(); //page&sort레포의 경우 findAll 반환이 Iterable타입임 -> List로 형변환
    }


    @RequestMapping(
        value = "/userInfos/{id}/buyplan",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public UserInfo buyPlan(
        @PathVariable(value = "id") Long id,
        @RequestBody BuyPlanCommand buyPlanCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /userInfo/buyPlan  called #####");
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(id);

        optionalUserInfo.orElseThrow(() -> new Exception("No Entity Found"));
        UserInfo userInfo = optionalUserInfo.get();
        userInfo.buyPlan(buyPlanCommand);

        userInfoRepository.save(userInfo);
        return userInfo;
    }
}
//>>> Clean Arch / Inbound Adaptor
