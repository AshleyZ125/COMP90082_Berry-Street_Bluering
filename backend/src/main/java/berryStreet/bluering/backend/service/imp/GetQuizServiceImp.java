package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.mapper.QuizMapper;
import berryStreet.bluering.backend.service.GetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetQuizServiceImp implements GetQuizService {
    @Autowired
    private QuizMapper quizMapper;
    @Override
    public List<Quiz> queryQuizList() {
        List<Quiz> quizes=quizMapper.queryQuizList();
        return quizes;
    }
}
