package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.QuizStatus;
import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.mapper.QuizMapper;
import berryStreet.bluering.backend.service.SetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
