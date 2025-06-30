package aivlemini.infra;

import aivlemini.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/userInfos")
@Transactional
public class UserInfoController {

    @Autowired
    UserInfoRepository userInfoRepository;

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
