package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.Experience;
import berryStreet.bluering.backend.service.UserExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
public class UserExpController {
    @Autowired
    private UserExpService userExpService;

    @GetMapping("/api/userExp/expList")
    public AjaxResult expList(){
        List<Experience> expList=userExpService.queryUserExpList();
        if (expList.isEmpty()) {
            return AjaxResult.warn("No experience now.");
        }
        return AjaxResult.success(expList);
    }

    @PostMapping("/api/userExp/addUserExp")
    public AjaxResult addUserExp(@RequestBody String eContent){
        if (eContent.isEmpty())
         return AjaxResult.error("Input empty!");
        int result=userExpService.insertUserExp(eContent);
        if (result != 0)
            return AjaxResult.success("Successful addition!");
        else
            return AjaxResult.error("Addition fail!");
    }


}
