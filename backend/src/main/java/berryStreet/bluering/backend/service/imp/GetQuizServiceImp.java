package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.Quiz;
import berryStreet.bluering.backend.mapper.QuizMapper;
import berryStreet.bluering.backend.service.GetQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class GetQuizServiceImp implements GetQuizService {
    @Autowired
    private QuizMapper quizMapper;

    @Override
    public HashMap<String,List<Quiz>> querySuperList(int UID) {
        HashMap<String,List<Quiz>> lists= new HashMap<>();
        List<Quiz> pubQuizes=quizMapper.queryPubQuizList(UID);
        List<Quiz> priQuizes=quizMapper.queryPriQuizList(UID);
        lists.put("public",pubQuizes);
        lists.put("private",priQuizes);
        return lists;
    }

    @Override
    public List<Quiz> queryLecList() {
        List<Quiz> list=quizMapper.queryQuizList();
        return list;
    }
}
