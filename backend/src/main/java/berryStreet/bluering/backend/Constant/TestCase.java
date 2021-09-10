package berryStreet.bluering.backend.Constant;

import berryStreet.bluering.backend.entity.Question;
import berryStreet.bluering.backend.entity.Quiz;

public class TestCase {

    /*Use for /api/quiz/setQuiz
    Quiz quiz=TestCase.Qz1;
     */
    public static final Quiz Qz1=new Quiz("topic1","overview1",3,1);

    /* Used for /api/quiz/setQuestion
    List<Question> questions = new ArrayList<>();
        questions.add(TestCase.Q1);
        questions.add(TestCase.Q2);
        questions.add(TestCase.Q3);
        questions.add(TestCase.Q5);
     */
    public static final Question Q1=new Question(1,"test1","test1",1);
    public static final Question Q2=new Question(2,"test2","test2",1);
    public static final Question Q3=new Question(3,"test3","test3",1);
    public static final Question Q4=new Question(4,"test4","test4",2);
    public static final Question Q5=new Question(5,"test5","test5",3);
}
