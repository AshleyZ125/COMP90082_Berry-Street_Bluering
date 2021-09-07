package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Feedback;
import berryStreet.bluering.backend.entity.Question;
import berryStreet.bluering.backend.entity.Quiz;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuizMapper {
    //*****************************************************
    // codes for supervisor
    @Select("select * from quiz where creatorID=#{creatorID} and status=2")
    public List<Quiz> queryPubQuizList(@Param("creatorID") int UID);
    @Select("select * from quiz where creatorID=#{creatorID} and status<2")
    public List<Quiz> queryPriQuizList(@Param("creatorID") int UID);

    @Select("select * from quiz where QID=#{QID}")
    public Quiz queryQuizByQID(@Param("QID") int QID);
    @Select("select * from question where quizID=#{quizID}")
    public Question queryQuestionByQID(@Param("quizID") int quizID);
    @Select("select * from feedback where quiz_feed_ID=#{quiz_feed_ID}")
    public Feedback queryFeedbackByQID(@Param("quiz_feed_ID") int quiz_feed_ID);

    @Insert("insert into `quiz` values(#{quiz.QID},#{quiz.topic},#{quiz.overview},#{quiz.status},#{quiz.creatorID})")
    @Options(useGeneratedKeys = true, keyProperty = "quiz.QID", keyColumn = "QID")
    public int createQuiz(@Param("quiz") Quiz quiz);

    @Insert("insert into `question` values(#{question.qID},#{question.qContent},#{question.options},#{question.quizID})")
    @Options(useGeneratedKeys = true, keyProperty = "question.qID", keyColumn = "qID")
    public int createQuestions(@Param("question") Question question);

    @Insert("insert into `feedback` values(#{feedback.FID},#{feedback.scoreRange},#{feedback.remark},#{feedback.quiz_feed_ID})")
    @Options(useGeneratedKeys = true, keyProperty = "feedback.FID", keyColumn = "FID")
    public int createFeedback(@Param("feedback") Feedback feedback);

    @Update("update quiz set topic = #{Quiz.topic},overview = #{Quiz.overview} where QID = #{Quiz.QID}")
    public int setQuiz(@Param("quiz") Quiz quiz);
    @Update("update quiz set status = #{status} where QID = #{QID}")
    public int setQuizStatus(@Param("QID") int QID,@Param("status") int status);



    //*****************************************************
    // codes for LEC
    @Select("select * from quiz where status=2")
    public List<Quiz> queryQuizList();

}
