package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.QuizStatus;
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
}
