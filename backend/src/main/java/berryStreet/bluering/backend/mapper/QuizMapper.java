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
    @Select("select * from quiz where creatorID=#{creatorID} and status=#{pubStatus}")
    public List<Quiz> queryPubQuizList(@Param("creatorID") int UID,@Param("pubStatus") int pubStatus);
    @Select("select * from quiz where creatorID=#{creatorID} and status<#{pubStatus}")
    public List<Quiz> queryPriQuizList(@Param("creatorID") int UID,@Param("pubStatus") int pubStatus);


    @Select("select * from quiz where QID=#{QID}")
    public Quiz queryQuizByQID(@Param("QID") int QID);
    @Select("select * from question where quizID=#{quizID}")
    public List<Question> queryQuestionByQID(@Param("quizID") int quizID);
    @Select("select * from feedback where quiz_feed_ID=#{quiz_feed_ID}")
    public List<Feedback> queryFeedbackByQID(@Param("quiz_feed_ID") int quiz_feed_ID);


    @Insert("insert into `quiz` values(#{quiz.QID},#{quiz.topic},#{quiz.overview},#{quiz.status},#{quiz.creatorID})")
    @Options(useGeneratedKeys = true, keyProperty = "QID", keyColumn = "QID")
    public int createQuiz(@Param("quiz") Quiz quiz);
    @Update("update quiz set topic = #{Quiz.topic},overview = #{Quiz.overview} where QID = #{Quiz.QID}")
    public int setQuiz(@Param("quiz") Quiz quiz);
    @Update("update quiz set status = #{status} where QID = #{QID}")
    public int setQuizStatus(@Param("QID") int QID,@Param("status") int status);

    @Insert("<script> insert into question (qContent,options,quizID) values  " +
            "  <foreach collection='questions' item='item' separator=',' > " +
            "  (#{item.qContent},#{item.options},#{item.quizID}) </foreach>" +
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "qID", keyColumn = "qID")
    public int createQuestions(@Param("questions") List<Question> questions);

    //IF(判断条件,为真执行这里,假执行这里)
    @Insert("<script> insert into question (qContent,options,quizID) values  " +
            "  <foreach collection='questions' item='item' separator=',' > " +
            "  (#{item.qContent},#{item.options},#{item.quizID}) </foreach>" +
            "ON DUPLICATE KEY UPDATE "+
            "qContent = IF(quizID = values(quizID),values(qContent),qContent), "+
            "options = IF(quizID = values(quizID),values(options),options) "+
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "qID", keyColumn = "qID")
    public int setQuestions(@Param("questions") List<Question> questions);

    @Delete("<script>delete from question where qID in " +
            "  <foreach collection='questions' item='item' open='(' separator=',' close=')'> " +
            "  #{item.qID} " +
            "  </foreach> </script>")
    public int deleteQuestions(@Param("questions") List<Question> questions);


    @Insert("insert into `feedback` values(#{feedback.FID},#{feedback.scoreRange},#{feedback.remark},#{feedback.quiz_feed_ID})")
    @Options(useGeneratedKeys = true, keyProperty = "feedback.FID", keyColumn = "FID")
    public int createFeedback(@Param("feedback") Feedback feedback);








    //*****************************************************
    // codes for LEC
    @Select("select * from quiz where status=#{pubStatus}")
    public List<Quiz> queryQuizList(@Param("pubStatus") int pubStatus);

}
