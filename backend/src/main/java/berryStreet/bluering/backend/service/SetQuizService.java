package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Feedback;
import berryStreet.bluering.backend.entity.Question;
import berryStreet.bluering.backend.entity.Quiz;

import java.util.List;

public interface SetQuizService {
    public int createQuiz(Quiz quiz);
    public int setQuiz(Quiz quiz);

    public int setQuestion(Question question);
    public int deleteQuestion(int qID);
    public int createQuestions(List<Question> questions);
    public int setQuestions(List<Question> questions);
    public int deleteQuestions(List<Question> questions);

    public int setFeedback(Feedback feedback);
    public int deleteFeedback(int FID);
    public int setFeedbacks(List<Feedback> feedbacks);
    public int deleteFeedbacks(List<Feedback> feedbacks);
    public int checkQuizStatus(int QID);
}
