package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.service.GetQuizService;
import berryStreet.bluering.backend.service.SetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class QuizController {
    @Autowired
    private GetQuizService getQuizService;
    @Autowired
    private SetQuizService setQuizService;

    @PostMapping("/api/quiz/superQuizList")
    private AjaxResult superQuizList(@RequestBody Quiz quiz){
        if (quiz==null||quiz.getCreatorID()<=0)
            return AjaxResult.error("Input empty!");
        HashMap<String,List<Quiz>> quizList=getQuizService.querySuperList(quiz.getCreatorID());
        if(quizList==null){
            return AjaxResult.warn("No quiz now.");
        }
        return AjaxResult.success(quizList);
    }

    @PostMapping("/api/quiz/lecQuizList")
    private AjaxResult lecQuizList(){
        List<Quiz> quizList=getQuizService.queryLecList();
        if(quizList==null){
            return AjaxResult.warn("No quiz now.");
        }
        return AjaxResult.success(quizList);
    }

    @PostMapping("/api/quiz/setQuiz")
    private AjaxResult setQuiz(@RequestBody Quiz quiz){
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        //Quiz checkQuiz=getQuizService.queryQuizByQID(quiz.getQID());
        //if(checkQuiz==null){
        if(quiz.getQID()==0){
            int result=setQuizService.createQuiz(quiz);
            if(result==0)
                return AjaxResult.success("Successful creation!");
            else
                return AjaxResult.error("Insert fail!");
        }else{
            int result=setQuizService.setQuiz(quiz);
            if(result==0)
                return AjaxResult.success("Successful update!");
            else
                return AjaxResult.error("update fail!");
        }
    }

}
