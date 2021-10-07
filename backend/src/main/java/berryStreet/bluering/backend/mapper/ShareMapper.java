package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShareMapper {
    @Insert("insert into share(sender,receiver,shareReflection,recordID)" +
            " values (#{share.sender},#{share.receiver},#{share.shareReflection},#{share.recordID},#{share" +
            ".quizTopic},#{share.rDate},#{share.username})")
    @Options(useGeneratedKeys = true, keyProperty = "SID", keyColumn = "SID")
    public int insertShare(@Param("share") Share share);
}
