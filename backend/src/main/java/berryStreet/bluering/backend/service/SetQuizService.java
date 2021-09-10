package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Feedback;
import berryStreet.bluering.backend.entity.Question;
import berryStreet.bluering.backend.entity.Quiz;

import java.util.List;

public interface SetQuizService {
    public int createQuiz(Quiz quiz);
    public int setQuiz(Quiz quiz);
    public int createQuestions(List<Question> questions);
    public int setQuestions(List<Question> questions);
    public int deleteQuestions(List<Question> questions);
    public int setFeedbacks(List<Feedback> feedbacks);
    public int deleteFeedbacks(List<Feedback> feedbacks);
    public int setQuizStatus(int QID,int status);
}
