package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.QuizStatus;
import berryStreet.bluering.backend.Exceptions.QuizQueryException;
import berryStreet.bluering.backend.Exceptions.ScoreRangeException;
import berryStreet.bluering.backend.entity.*;
import berryStreet.bluering.backend.mapper.QuizMapper;
import berryStreet.bluering.backend.service.GetQuizService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GetQuizServiceImp implements GetQuizService {
    @Autowired
    private QuizMapper quizMapper;

    @Override
    public HashMap<String, List<Quiz>> querySuperList(int UID) {
        HashMap<String, List<Quiz>> lists = new HashMap<>();
        List<Quiz> pubQuizes = quizMapper.queryPubQuizList(UID, QuizStatus.QUIZ_PUBLIC);
        List<Quiz> priQuizes = quizMapper.queryPriQuizList(UID, QuizStatus.QUIZ_PUBLIC);
        lists.put("public", pubQuizes);
        lists.put("private", priQuizes);
        return lists;
    }

    @Override
    public List<Quiz> queryLecList() {
        List<Quiz> list = quizMapper.queryQuizList(QuizStatus.QUIZ_PUBLIC);
        return list;
    }

    @Override
    public Quiz queryQuizByQID(int QID) {
        return quizMapper.queryQuizByQID(QID);
    }

    @Override
    public List<Question> queryQuestionByQID(int quizID) {
        return quizMapper.queryQuestionByQID(quizID);
    }

    @Override
    public List<Feedback> queryFeedbackByQID(int quiz_feed_ID) {
        return quizMapper.queryFeedbackByQID(quiz_feed_ID);
    }

    @Override
    public Feedback queryFeedbackByResult(int QID, int score) throws ScoreRangeException {
        List<Feedback> feedbacks = quizMapper.queryFeedbackByQID(QID);
        try {
            String scoreRange = getRangeByScore(feedbacks, score);
            return quizMapper.queryFeedbackByResult(QID, scoreRange);
        } catch (ScoreRangeException e) {
            throw new ScoreRangeException();
        }

    }

    @Override
    public TakeQuizDTO queryQuizTakenByQID(int QID) throws QuizQueryException {
        Quiz quiz = quizMapper.queryQuizByQID(QID);
        if (quiz == null) {
            throw new QuizQueryException();
        }
        List<Question> questions = quizMapper.queryQuestionByQID(QID);
        TakeQuizDTO takeQuizDTO = TakeQuizDTO.builder()
                .QID(quiz.getQID())
                .overview(quiz.getOverview())
                .topic(quiz.getTopic())
                .questionList(questions)
                .build();
        return takeQuizDTO;
    }

    public String getRangeByScore(List<Feedback> feedbacks, int score) throws ScoreRangeException {
        String scoreRange = null;
        for (Feedback feedback : feedbacks) {
            String range = feedback.getScoreRange();
            String[] borders = range.split(";");
            if (score >= Integer.parseInt(borders[0]) && score < Integer.parseInt(borders[1])) {
                scoreRange = range;
                break;
            }
        }
        if (null == scoreRange) {
            throw new ScoreRangeException();
        }
        return scoreRange;
    }
}
