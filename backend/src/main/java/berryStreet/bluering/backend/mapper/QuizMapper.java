package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Quiz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuizMapper {
    @Select("select * from quiz")
    public List<Quiz> queryQuizList();
}
