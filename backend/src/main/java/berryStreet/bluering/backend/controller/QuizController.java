package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Constant.TestCase;
import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.Question;
import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.service.GetQuizService;
import berryStreet.bluering.backend.service.SetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        //int currSupervisor=1;
        int currSupervisor=quiz.getCreatorID();
        if (quiz==null||currSupervisor<=0)
            return AjaxResult.error("Input empty!");
        HashMap<String,List<Quiz>> quizList=getQuizService.querySuperList(currSupervisor);
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
        System.out.println("input:"+quiz);
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        //Quiz checkQuiz=getQuizService.queryQuizByQID(quiz.getQID());
        //if(checkQuiz==null){
        if(quiz.getQID()<=0){
            int result=setQuizService.createQuiz(quiz);
            System.out.println("create:"+quiz);
            if(result!=0)
                return AjaxResult.success("Successful creation!");
            else
                return AjaxResult.error("Insert fail!");
        }else{
            int result=setQuizService.setQuiz(quiz);
            System.out.println("set:"+quiz);
            if(result!=0)
                return AjaxResult.success("Successful update!");
            else
                return AjaxResult.error("update fail!");
        }
    }


    @PostMapping("/api/quiz/setQuestion")
    private AjaxResult setQuestion(@RequestBody List<Question> questions){
        if(questions==null)
            return AjaxResult.error("Input empty!");
        int quizID=questions.get(0).getQuizID();
        List<Question> prevQuestions=getQuizService.queryQuestionByQID(quizID);
        int result=0;
        System.out.println("input:"+questions.toString());
        System.out.println("prev:"+prevQuestions.toString());
        if(prevQuestions.isEmpty()){
            result=setQuizService.createQuestions(questions);
        }else{
            boolean isCreated=true;
            for(int i=0;i<questions.size();i++){
                Question currQues=questions.get(i);
                for(int j=0;j<prevQuestions.size();j++){
                    if(currQues.getqID()==prevQuestions.get(j).getqID()){
                        prevQuestions.remove(j);
                        isCreated=false;
                        break;
                    }
                }
                if(isCreated)
                    currQues.setqID(0);
                else
                    isCreated=true;
            }
            System.out.println("delete:"+prevQuestions.toString());
            if(!prevQuestions.isEmpty()){
                int deleteResult=setQuizService.deleteQuestions(prevQuestions);
                if(deleteResult==0){
                    System.out.println("Delete fail!");
                    return AjaxResult.error(" Fail! delete fail!");
                }
            }
            result=setQuizService.setQuestions(questions);
        }
        System.out.println("set:"+questions.toString());
        if(result!=0)
            return AjaxResult.success("Successful update!");
        else
            return AjaxResult.error("Insert fail!");
    }

}
