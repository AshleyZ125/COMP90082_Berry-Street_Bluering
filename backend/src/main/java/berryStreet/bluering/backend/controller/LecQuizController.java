package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.service.GetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class LecQuizController {
    @Autowired
    private GetQuizService getQuizService;

    @PostMapping("/api/lecQuiz/getList")
    private AjaxResult getQuizList(){
        List<Quiz> quizList=getQuizService.queryQuizList();
        if(quizList==null){
            return AjaxResult.warn("No quiz now.");
        }
        return AjaxResult.success(quizList);
    }
}
