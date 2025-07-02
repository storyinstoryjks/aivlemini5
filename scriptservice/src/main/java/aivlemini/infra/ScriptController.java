package aivlemini.infra;

import aivlemini.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/scripts")
@Transactional
public class ScriptController {

    @Autowired
    ScriptRepository scriptRepository;

    @RequestMapping(
        value = "/scripts/{id}/requestpublish",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Script requestPublish(
        @PathVariable(value = "id") Long id,
        @RequestBody(required = false) RequestPublishCommand requestPublishCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /script/requestPublish  called #####");
        Optional<Script> optionalScript = scriptRepository.findById(id);

        optionalScript.orElseThrow(() -> new Exception("No Entity Found"));
        Script script = optionalScript.get();
        script.requestPublish(requestPublishCommand);

        scriptRepository.save(script);
        return script;
    }

    @RequestMapping(
        value = "/scripts/{id}/savetemporaryscript",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Script saveTemporaryScript(
        @PathVariable(value = "id") Long id,
        @RequestBody SaveTemporaryScriptCommand saveTemporaryScriptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /script/saveTemporaryScript  called #####");
        Optional<Script> optionalScript = scriptRepository.findById(id);

        optionalScript.orElseThrow(() -> new Exception("No Entity Found"));
        Script script = optionalScript.get();
        script.saveTemporaryScript(saveTemporaryScriptCommand);

        scriptRepository.save(script);
        return script;
    }
 
    @RequestMapping(
        value = "/scripts/savescript",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Script saveScript(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody SaveScriptCommand saveScriptCommand
    ) throws Exception {
        System.out.println("##### /script/saveScript  called #####");
        Script script = new Script();
        script.saveScript(saveScriptCommand);
        scriptRepository.save(script);
        return script;
    }

}
//>>> Clean Arch / Inbound Adaptor
