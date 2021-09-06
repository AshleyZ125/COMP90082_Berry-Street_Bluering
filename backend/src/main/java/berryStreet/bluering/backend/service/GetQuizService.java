package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Quiz;

import java.util.HashMap;
import java.util.List;

public interface GetQuizService {
    public HashMap<String,List<Quiz>> querySuperList(int UID);
    public List<Quiz> queryLecList();
}
