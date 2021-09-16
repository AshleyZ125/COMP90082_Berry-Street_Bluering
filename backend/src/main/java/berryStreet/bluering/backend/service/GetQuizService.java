package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.Exceptions.QuizQueryException;
import berryStreet.bluering.backend.entity.*;

import java.util.HashMap;
import java.util.List;

public interface GetQuizService {
    public HashMap<String,List<Quiz>> querySuperList(int UID);
    public List<Quiz> queryLecList();
    public Quiz queryQuizByQID(int QID);
    public List<Question> queryQuestionByQID(int quizID);
    public List<Feedback> queryFeedbackByQID(int quiz_feed_ID);
    public TakeQuizDTO queryQuizTakenByQID(int QID) throws QuizQueryException;
}
