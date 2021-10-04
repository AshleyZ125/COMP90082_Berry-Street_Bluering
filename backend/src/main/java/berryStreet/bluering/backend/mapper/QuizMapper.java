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
    public List<Quiz> queryPubQuizList(@Param("creatorID") int UID, @Param("pubStatus") int pubStatus);

    @Select("select * from quiz where creatorID=#{creatorID} and status<#{pubStatus}")
    public List<Quiz> queryPriQuizList(@Param("creatorID") int UID, @Param("pubStatus") int pubStatus);


    @Select("select * from quiz where QID=#{QID}")
    public Quiz queryQuizByQID(@Param("QID") int QID);

    @Select("select * from question where quizID=#{quizID}")
    public List<Question> queryQuestionByQID(@Param("quizID") int quizID);

    @Select("select count(qID) from question where quizID=#{quizID}")
    public int checkQuestionNumber(@Param("quizID") int quizID);

    @Select("select * from feedback where quiz_feed_ID=#{quiz_feed_ID}")
    public List<Feedback> queryFeedbackByQID(@Param("quiz_feed_ID") int quiz_feed_ID);

    @Select("select * from feedback where quiz_feed_ID=#{quiz_feed_ID} and scoreRange = " +
            "#{scoreRange}")
    public Feedback queryFeedbackByResult(@Param("quiz_feed_ID") int quiz_feed_ID, @Param(
            "scoreRange") String scoreRange);

    @Select("select count(FID) from feedback where quiz_feed_ID=#{quiz_feed_ID}")
    public int checkFeedbackNumber(@Param("quiz_feed_ID") int quiz_feed_ID);


    // Quiz creation and update
    @Insert("insert into quiz(topic,overview,status,creatorID) values(#{quiz.topic},#{quiz.overview},#{quiz.status},#{quiz.creatorID})")
    @Options(useGeneratedKeys = true, keyProperty = "QID", keyColumn = "QID")
    public int createQuiz(@Param("quiz") Quiz quiz);

    @Update("update quiz set topic = #{quiz.topic},overview = #{quiz.overview} where QID = #{quiz.QID}")
    public int setQuiz(@Param("quiz") Quiz quiz);

    @Update("update quiz set status = #{status} where QID = #{QID}")
    public int setQuizStatus(@Param("QID") int QID, @Param("status") int status);


    // Question creation and update
    @Insert("<script> insert into question (qID,qContent,options,quizID) values  " +
            "(#{question.qID},#{question.qContent},#{question.options},#{question.quizID}) " +
            "ON DUPLICATE KEY UPDATE " +
            "qContent = IF(quizID = values(quizID),values(qContent),qContent), " +
            "options = IF(quizID = values(quizID),values(options),options) " +
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "qID", keyColumn = "qID")
    public int setQuestion(@Param("question") Question question);

    @Delete("delete from question where qID=#{qID} ")
    public int deleteQuestion(@Param("qID") int qID);

    @Insert("<script> insert into question (qContent,options,quizID) values  " +
            "  <foreach collection='questions' item='item' separator=',' > " +
            "  (#{item.qContent},#{item.options},#{item.quizID}) </foreach>" +
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "qID", keyColumn = "qID")
    public int createQuestions(@Param("questions") List<Question> questions);

    @Insert("<script> insert into question (qID,qContent,options,quizID) values  " +
            "  <foreach collection='questions' item='item' separator=',' > " +
            "  (#{item.qID},#{item.qContent},#{item.options},#{item.quizID}) </foreach>" +
            "ON DUPLICATE KEY UPDATE " +
            "qContent = IF(quizID = values(quizID),values(qContent),qContent), " +
            "options = IF(quizID = values(quizID),values(options),options) " +
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "qID", keyColumn = "qID")
    public int setQuestions(@Param("questions") List<Question> questions);

    @Delete("<script>delete from question where qID in " +
            "  <foreach collection='questions' item='item' open='(' separator=',' close=')'> " +
            "  #{item.qID} " +
            "  </foreach> </script>")
    public int deleteQuestions(@Param("questions") List<Question> questions);


    // Feedback creation and update
    @Insert("<script> insert into feedback (FID,scoreRange,remark,quiz_feed_ID) values " +
            "(#{feedback.FID},#{feedback.scoreRange},#{feedback.remark},#{feedback.quiz_feed_ID}) " +
            "ON DUPLICATE KEY UPDATE " +
            "scoreRange = IF(quiz_feed_ID = values(quiz_feed_ID),values(scoreRange),scoreRange), " +
            "remark = IF(quiz_feed_ID = values(quiz_feed_ID),values(remark),remark) " +
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "FID", keyColumn = "FID")
    public int setFeedback(@Param("feedback") Feedback feedback);

    @Delete("delete from feedback where FID=#{FID} ")
    public int deleteFeedback(@Param("FID") int FID);

    @Insert("<script> insert into feedback (FID,scoreRange,remark,quiz_feed_ID) values  " +
            "  <foreach collection='feedbacks' item='item' separator=',' > " +
            "  (#{item.FID},#{item.scoreRange},#{item.remark},#{item.quiz_feed_ID}) </foreach>" +
            "ON DUPLICATE KEY UPDATE " +
            "scoreRange = IF(quiz_feed_ID = values(quiz_feed_ID),values(scoreRange),scoreRange), " +
            "remark = IF(quiz_feed_ID = values(quiz_feed_ID),values(remark),remark) " +
            "   </script>")
    @Options(useGeneratedKeys = true, keyProperty = "FID", keyColumn = "FID")
    public int setFeedbacks(@Param("feedbacks") List<Feedback> feedbacks);

    @Delete("<script>delete from feedback where FID in " +
            "  <foreach collection='feedbacks' item='item' open='(' separator=',' close=')'> " +
            "  #{item.FID} " +
            "  </foreach> </script>")
    public int deleteFeedbacks(@Param("feedbacks") List<Feedback> feedbacks);


    //*****************************************************
    // codes for LEC
    @Select("select * from quiz where status=#{pubStatus}")
    public List<Quiz> queryQuizList(@Param("pubStatus") int pubStatus);

}
