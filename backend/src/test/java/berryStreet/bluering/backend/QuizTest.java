package berryStreet.bluering.backend;

import berryStreet.bluering.backend.controller.QuizController;
import berryStreet.bluering.backend.entity.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuizTest {
    @Autowired
    QuizController quizController;
    @Test
    public void superQuizListTest(){
        int unValidSuperID=0;
        quizController.superQuizList(unValidSuperID);
        int existSuperID=1;
        quizController.superQuizList(existSuperID);
        int unExistSuperID=100;
        quizController.superQuizList(unExistSuperID);
    }

    @Test
    public void lecQuizListTest(){
        quizController.lecQuizList();
    }

    @Test
    public void getQuizTest(){
        int unValidQuizID=0;
        quizController.getQuiz(unValidQuizID);
        int existQuizID=1;
        quizController.getQuiz(existQuizID);
        int unExistQuizID=100;
        quizController.getQuiz(unExistQuizID);
    }

    @Test
    public void getQuestionTest(){
        int unValidQuizID=0;
        quizController.getQuestion(unValidQuizID);
        int existQuizID=1;
        quizController.getQuestion(existQuizID);
        int unExistQuizID=100;
        quizController.getQuestion(unExistQuizID);
    }

    @Test
    public void setQuizTest(){
        Quiz createQuiz=Quiz.builder().QID(0).topic("learning").overview("this is learning").creatorID(1).status(1).build();
    }

    @Test
    public void setQuestionTest(){

    }

}
