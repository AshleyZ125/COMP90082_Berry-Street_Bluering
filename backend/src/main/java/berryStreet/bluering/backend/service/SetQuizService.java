package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Quiz;

public interface SetQuizService {
    public int createQuiz(Quiz quiz);
    public int setQuiz(Quiz quiz);
}
