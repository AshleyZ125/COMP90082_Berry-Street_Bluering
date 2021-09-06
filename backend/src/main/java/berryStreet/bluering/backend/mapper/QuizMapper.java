package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Quiz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuizMapper {
    @Select("select * from quiz where creatorID=#{creatorID} and status=2")
    public List<Quiz> queryPubQuizList(@Param("creatorID") int UID);
    @Select("select * from quiz where creatorID=#{creatorID} and status<2")
    public List<Quiz> queryPriQuizList(@Param("creatorID") int UID);

    @Select("select * from quiz where status=2")
    public List<Quiz> queryQuizList();
}
