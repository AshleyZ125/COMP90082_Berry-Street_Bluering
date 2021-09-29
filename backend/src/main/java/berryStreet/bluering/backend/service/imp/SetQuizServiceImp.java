package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.QuizStatus;
import berryStreet.bluering.backend.entity.Feedback;
import berryStreet.bluering.backend.entity.Question;
import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.mapper.QuizMapper;
import berryStreet.bluering.backend.service.SetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetQuizServiceImp implements SetQuizService {
    @Autowired
    private QuizMapper quizMapper;

    @Override
    public int createQuiz(Quiz quiz) {
        quiz.setStatus(QuizStatus.QUIZ_EDIT);
        return quizMapper.createQuiz(quiz);
    }

    @Override
    public int setQuiz(Quiz quiz) {
        return quizMapper.setQuiz(quiz);
    }

    @Override
    public int setQuestion(Question question) {
        return quizMapper.setQuestion(question);
    }

    @Override
    public int deleteQuestion(int qID) {
        return quizMapper.deleteQuestion(qID);
    }

    @Override
    public int createQuestions(List<Question> questions) {
        return quizMapper.createQuestions(questions);
    }

    @Override
    public int setQuestions(List<Question> questions) {
        return quizMapper.setQuestions(questions);
    }

    @Override
    public int deleteQuestions(List<Question> questions) {
        return quizMapper.deleteQuestions(questions);
    }

    @Override
    public int setFeedback(Feedback feedback) {
        return quizMapper.setFeedback(feedback);
    }

    @Override
    public int deleteFeedback(int FID) {
        return quizMapper.deleteFeedback(FID);
    }

    @Override
    public int setFeedbacks(List<Feedback> feedbacks) {
        return quizMapper.setFeedbacks(feedbacks);
    }

    @Override
    public int deleteFeedbacks(List<Feedback> feedbacks) {
        return quizMapper.deleteFeedbacks(feedbacks);
    }

    @Override
    public int checkQuizStatus(int QID) {
        int currStatus=quizMapper.queryQuizByQID(QID).getStatus();
        int quesNum=quizMapper.checkQuestionNumber(QID);
        int feedNum=quizMapper.checkFeedbackNumber(QID);
        if(quesNum==0||feedNum==0) {
            System.out.println("edit:"+QID);
            return quizMapper.setQuizStatus(QID, QuizStatus.QUIZ_EDIT);
        }else if(currStatus==QuizStatus.QUIZ_EDIT) {
            System.out.println("save:"+QID);
            return quizMapper.setQuizStatus(QID, QuizStatus.QUIZ_SAVED);
        }else return 1;
    }

    @Override
    public int setQuizStatus(int QID, int status) {
        return quizMapper.setQuizStatus(QID,status);
    }
}
