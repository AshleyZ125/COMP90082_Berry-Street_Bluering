package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Constant.QuizStatus;
import berryStreet.bluering.backend.Constant.TestCase;
import berryStreet.bluering.backend.Exceptions.QuizQueryException;
import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.*;
import berryStreet.bluering.backend.service.GetQuizService;
import berryStreet.bluering.backend.service.SetQuizService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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


    @GetMapping("/api/quiz/superQuizList/{UID}")
    public AjaxResult superQuizList(@PathVariable("UID") int creatorID){
        //int currSupervisor=1;
        //int currSupervisor=quiz.getCreatorID();
        if (creatorID<=0)
            return AjaxResult.error("Input empty!");
        HashMap<String,List<Quiz>> quizList=getQuizService.querySuperList(creatorID);
        if(quizList==null){
            return AjaxResult.warn("No quiz now.");
        }
        return AjaxResult.success(quizList);
    }

    @PostMapping("/api/quiz/lecQuizList")
    public AjaxResult lecQuizList(){
        List<Quiz> quizList=getQuizService.queryLecList();
        if(quizList==null){
            return AjaxResult.warn("No quiz now.");
        }
        return AjaxResult.success(quizList);
    }

    @GetMapping("/api/quiz/getQuiz/{QID}")
    public AjaxResult getQuiz(@PathVariable("QID") int QID){
        if(QID==0)
            return AjaxResult.error("Input empty!");
        Quiz result=getQuizService.queryQuizByQID(QID);
        if(result==null){
            return AjaxResult.warn("No this quiz!");
        }else
            return AjaxResult.success(result);
    }

    //@RequestBody Quiz quiz
    @GetMapping("/api/quiz/getQuestion/{QID}")
    public AjaxResult getQuestion(@PathVariable("QID") int QID){
        if(QID==0)
            return AjaxResult.error("Input empty!");
        List<Question> questions=getQuizService.queryQuestionByQID(QID);
        System.out.println("questions:"+questions);
        List<QuestionVO> questionVOs = convert(questions);
        if(questions==null){
            return AjaxResult.warn("No question in this quiz!");
        }else
            return AjaxResult.success(questionVOs);
    }

    @GetMapping("/api/quiz/getFeedback/{QID}")
    private AjaxResult getFeedback(@PathVariable("QID") int QID){
        if(QID==0)
            return AjaxResult.error("Input empty!");
        List<Feedback> feedbacks=getQuizService.queryFeedbackByQID(QID);
        if(feedbacks==null){
            return AjaxResult.warn("No feedback in this quiz!");
        }else
            return AjaxResult.success(feedbacks);
    }


    //@RequestBody Quiz quiz
    @GetMapping("/api/quiz/setQuiz")
    private AjaxResult setQuiz(){
        Quiz quiz=Quiz.builder().QID(7).status(4).build();
        System.out.println("input:"+quiz);
        if(quiz==null)
            return AjaxResult.error("Input empty!");
        if(quiz.getQID()<=0){
            int result=setQuizService.createQuiz(quiz);
            System.out.println("create:"+quiz);
            if(result!=0) {
                int QID=quiz.getQID();
                return AjaxResult.success("Successful creation!",QID);
            }
            else
                return AjaxResult.error("Insert fail!");
        }else{
            if(quiz.getStatus()==QuizStatus.QUIZ_DELETED){
                int result=setQuizService.setQuizStatus(quiz.getQID(),QuizStatus.QUIZ_DELETED);
                System.out.println("delete:"+quiz);
                if(result!=0)
                    return AjaxResult.success("Successful delete!");
                else
                    return AjaxResult.error("deletion fail!");
            }else{
                int result=setQuizService.setQuiz(quiz);
                System.out.println("set:"+quiz);
                if(result!=0)
                    return AjaxResult.success("Successful update!");
                else
                    return AjaxResult.error("update fail!");
            }
        }
    }


    @PostMapping("/api/quiz/setQuestion")
    private AjaxResult setQuestion(@RequestBody QuestionVO questionVO){
        if(questionVO==null)
            return AjaxResult.error("Input empty!");
        int result;
        if(questionVO.getQuizID()<=0){
            result=setQuizService.deleteQuestion(questionVO.getQID());
            if(result==0) return AjaxResult.error("Delete fail!");
        }else{
            Question question = null;
            List<Option> options = questionVO.getOptions();
            String optionsString = JSON.toJSONString(options);
            question = Question.builder()
                    .options(optionsString)
                    .qContent(questionVO.getQContent())
                    .qID(questionVO.getQID())
                    .quizID(questionVO.getQuizID())
                    .build();
            result=setQuizService.setQuestion(question);
            if(result==0) return AjaxResult.error("Update fail!");
        }
        return AjaxResult.success("Successful update!");
    }




    @PostMapping("/api/quiz/setFeedback")
    private AjaxResult setFeedback(@RequestBody Feedback feedback){
        if(feedback==null)
            return AjaxResult.error("Input empty!");
        int result;
        if(feedback.getQuiz_feed_ID()<=0){
            result=setQuizService.deleteFeedback(feedback.getFID());
            if(result==0) return AjaxResult.error("Delete fail!");
        }else{
            result=setQuizService.setFeedback(feedback);
            if(result==0) return AjaxResult.error("Update fail!");
        }
        return AjaxResult.success("Successful update!");
    }


    @GetMapping("/api/quiz/checkQuizStatus/{QID}")
    private AjaxResult checkQuizStatus(@PathVariable("QID") int QID){
        if(QID==0)
            return AjaxResult.error("Input empty!");
        int result=setQuizService.checkQuizStatus(QID);
        if(result==0){
            System.out.println("Status check fail!");
            return AjaxResult.error(" Fail! Status check fail!");
        }else
            return AjaxResult.success("Quiz status check pass!");
    }

    @PostMapping("/api/quiz/setQuizStatus")
    private AjaxResult setQuizStatus(@RequestBody Quiz quiz){
        if(quiz.getQID()==0)
            return AjaxResult.error("Input empty!");
        int QID=quiz.getQID();
        Quiz currQuiz=getQuizService.queryQuizByQID(QID);
        switch (quiz.getStatus()){
            case QuizStatus.QUIZ_SAVED:
                if(currQuiz.getStatus()==QuizStatus.QUIZ_PUBLIC){
                    int result=setQuizService.setQuizStatus(QID,QuizStatus.QUIZ_SAVED);
                    if (result!=0)
                        return AjaxResult.success("Quiz private success!");
                }else if(currQuiz.getStatus()==QuizStatus.QUIZ_SAVED){
                    return AjaxResult.warn("Operation cancel! Current status has been private!");
                }
                break;
            case QuizStatus.QUIZ_PUBLIC:
                if(currQuiz.getStatus()==QuizStatus.QUIZ_PUBLIC){
                    return AjaxResult.warn("Operation cancel! Current status has been public!");
                }else if(currQuiz.getStatus()==QuizStatus.QUIZ_SAVED){
                    int result=setQuizService.setQuizStatus(QID,QuizStatus.QUIZ_PUBLIC);
                    if (result!=0)
                        return AjaxResult.success("Quiz public success!");
                }
                break;
            case QuizStatus.QUIZ_EDIT:
                if(currQuiz.getStatus()!=QuizStatus.QUIZ_DELETED){
                    int result=setQuizService.setQuizStatus(QID,QuizStatus.QUIZ_EDIT);
                    if (result!=0)
                        return AjaxResult.success("Quiz status protection success!");
                }else{
                    return AjaxResult.error("The quiz is not exist!");
                }
                break;
            default:
                return AjaxResult.error("Operation not allow!");
        }
        System.out.println("Status change fail!");
        return AjaxResult.error(" Fail! Status change fail!");
    }


    @PostMapping("/api/quiz/setMeltiQuestion")
    private AjaxResult setMeltiQuestion(@RequestBody List<QuestionVO> questionVOs){
        if(questionVOs==null)
            return AjaxResult.error("Input empty!");
        int quizID=questionVOs.get(0).getQuizID();
        List<Question> prevQuestions=getQuizService.queryQuestionByQID(quizID);
        int result=0;
        System.out.println("input:"+questionVOs.toString());
        System.out.println("prev:"+prevQuestions.toString());
        List<Question> questions = convertQuestion(questionVOs);
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


    @GetMapping("/api/quiz/takeQuiz")
    private AjaxResult takeQuiz(int QID){
        TakeQuizDTO takeQuizDTO = null;
        try{
            takeQuizDTO = getQuizService.queryQuizTakenByQID(QID);
        } catch (QuizQueryException e) {
            return AjaxResult.error("no such quiz");
        }
        if(takeQuizDTO == null){
            return AjaxResult.error("cannot get quiz,but quiz exit");
        }
        TakeQuizVO takeQuizVO = TakeQuizVO.builder()
                .overview(takeQuizDTO.getOverview())
                .questionList(convert(takeQuizDTO.getQuestionList()))
                .QID(takeQuizDTO.getQID())
                .topic(takeQuizDTO.getTopic())
                .build();
        return AjaxResult.success(takeQuizVO);
    }

    public List<QuestionVO> convert(List<Question> questions){
        List<QuestionVO> questionVOs = new ArrayList<>();
        for (Question question :questions){
            List<Option> options = JSON.parseObject(question.getOptions(),
                    new TypeReference<List<Option>>(){}.getType());
            QuestionVO questionVO = QuestionVO.builder()
                    .options(options)
                    .qContent(question.getqContent())
                    .qID(question.getqID())
                    .quizID(question.getQuizID())
                    .build();
            questionVOs.add(questionVO);
        }
        return questionVOs;
    }

    public List<Question> convertQuestion(List<QuestionVO> questionVOs){
        List<Question> questions = new ArrayList<>();
        for (QuestionVO questionVO : questionVOs){
            Question question = Question.builder()
                    .quizID(questionVO.getQuizID())
                    .qID(questionVO.getQID())
                    .qContent(questionVO.getQContent())
                    .options(JSON.toJSONString(questionVO.getOptions()))
                    .build();
            questions.add(question);
        }
        return questions;
    }
}
