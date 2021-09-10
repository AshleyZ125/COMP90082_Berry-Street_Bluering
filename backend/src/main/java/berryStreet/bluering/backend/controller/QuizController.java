package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Constant.QuizStatus;
import berryStreet.bluering.backend.Constant.TestCase;
import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.Feedback;
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

    @PostMapping("/api/quiz/getQuiz")
    private AjaxResult getQuiz(@RequestBody Quiz quiz){
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        Quiz result=getQuizService.queryQuizByQID(quiz.getQID());
        if(result==null){
            return AjaxResult.warn("No this quiz!");
        }else
            return AjaxResult.success(result);
    }

    @PostMapping("/api/quiz/getQuestion")
    private AjaxResult getQuestion(@RequestBody Quiz quiz){
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        List<Question> questions=getQuizService.queryQuestionByQID(quiz.getQID());
        if(questions==null){
            return AjaxResult.warn("No question in this quiz!");
        }else
            return AjaxResult.success(questions);
    }

    @PostMapping("/api/quiz/getFeedback")
    private AjaxResult getFeedback(@RequestBody Quiz quiz){
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        List<Feedback> feedbacks=getQuizService.queryFeedbackByQID(quiz.getQID());
        if(feedbacks==null){
            return AjaxResult.warn("No feedback in this quiz!");
        }else
            return AjaxResult.success(feedbacks);
    }


    @PostMapping("/api/quiz/setQuiz")
    private AjaxResult setQuiz(@RequestBody Quiz quiz){
        System.out.println("input:"+quiz);
        if(quiz==null)
            return AjaxResult.error("Input empty!");
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
    private AjaxResult setQuestion(@RequestBody Question question){
        if(question==null)
            return AjaxResult.error("Input empty!");
        int result;
        if(question.getQuizID()<=0){
            result=setQuizService.deleteQuestion(question.getqID());
            if(result==0) return AjaxResult.error("delete fail!");
        }else{
            result=setQuizService.setQuestion(question);
            if(result==0) return AjaxResult.error("update fail!");
        }
        return AjaxResult.success("Successful update!");
    }

    //@RequestBody Feedback feedback
    @GetMapping("/api/quiz/setFeedback")
    private AjaxResult setFeedback(){
        Feedback feedback=TestCase.F4;
        if(feedback==null)
            return AjaxResult.error("Input empty!");
        int result;
        if(feedback.getQuiz_feed_ID()<=0){
            result=setQuizService.deleteFeedback(feedback.getFID());
            if(result==0) return AjaxResult.error("delete fail!");
        }else{
            result=setQuizService.setFeedback(feedback);
            if(result==0) return AjaxResult.error("update fail!");
        }
        return AjaxResult.success("Successful update!");
    }


    @PostMapping("/api/quiz/checkQuizStatus")
    private AjaxResult checkQuizStatus(@RequestBody Quiz quiz){
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        int result=setQuizService.checkQuizStatus(quiz.getQID());
        if(result==0){
            System.out.println("Status check fail!");
            return AjaxResult.error(" Fail! Status check fail!");
        }else
            return AjaxResult.success("Quiz status check pass!");
    }


    @PostMapping("/api/quiz/setMeltiQuestion")
    private AjaxResult setMeltiQuestion(@RequestBody List<Question> questions){
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
                if(isCreated) currQues.setqID(0);
                else isCreated=true;
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
        if(result==0) return AjaxResult.error("Update fail!");
        result=setQuizService.checkQuizStatus(quizID);
        if(result==0){
            System.out.println("Status change fail!");
            return AjaxResult.error(" Fail! Status change fail!");
        }
        return AjaxResult.success("Successful update!");
    }


    @PostMapping("/api/quiz/setMeltiFeedback")
    private AjaxResult setMeltiFeedback(@RequestBody List<Feedback> feedbacks){
        if(feedbacks==null)
            return AjaxResult.error("Input empty!");
        int quizID=feedbacks.get(0).getQuiz_feed_ID();
        List<Feedback> prevFeedbacks=getQuizService.queryFeedbackByQID(quizID);
        int result=0;
        //boolean toSave=false;
        System.out.println("input:"+feedbacks.toString());
        System.out.println("prev:"+prevFeedbacks.toString());
        if(!prevFeedbacks.isEmpty()){
            for(int i=0;i<feedbacks.size();i++){
                Feedback currFeed=feedbacks.get(i);
                for(int j=0;j<prevFeedbacks.size();j++){
                    if(currFeed.getQuiz_feed_ID()==prevFeedbacks.get(j).getQuiz_feed_ID()){
                        prevFeedbacks.remove(j);
                        break;
                    }
                }
            }
            System.out.println("delete:"+prevFeedbacks.toString());
            if(!prevFeedbacks.isEmpty()){
                int deleteResult=setQuizService.deleteFeedbacks(prevFeedbacks);
                if(deleteResult==0){
                    System.out.println("Delete fail!");
                    return AjaxResult.error(" Fail! delete fail!");
                }
            }
        }
        System.out.println("set:"+feedbacks.toString());
        result=setQuizService.setFeedbacks(feedbacks);
        if(result==0) return AjaxResult.error("update fail!");
        result=setQuizService.checkQuizStatus(quizID);
        if(result==0){
            System.out.println("Status change fail!");
            return AjaxResult.error(" Fail! Status change fail!");
        }
        return AjaxResult.success("Successful update!");
    }

}
