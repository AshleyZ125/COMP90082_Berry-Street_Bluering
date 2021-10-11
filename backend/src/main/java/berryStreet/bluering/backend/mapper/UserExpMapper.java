package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Experience;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserExpMapper {

    @Select("select * from experience")
    public List<Experience> queryUserExpList();

    @Insert("insert into `experience` values(#{exp.EID},#{exp.eContent},#{exp.eDate})")
    @Options(useGeneratedKeys = true, keyProperty = "exp.EID", keyColumn = "EID")
    public int insertUserExp(@Param("exp") Experience exp);
}
