package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Share;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShareMapper {
    @Insert("insert into share(sender,receiver,shareReflection,recordID)" +
            " values (#{share.sender},#{share.receiver},#{share.shareReflection},#{share.recordID})")
    @Options(useGeneratedKeys = true, keyProperty = "SID", keyColumn = "SID")
    public int insertShare(@Param("share") Share share);

    @Update("update share set sender = #{share.sender},receiver = #{share.receiver}," +
            "shareReflection = #{share.shareReflection} where " +
            "recordID = #{RID}")
    public int updateShare(@Param("share") Share share, @Param("RID") int RID);
    @Select("select * from share where recordID = #{RID}")
    public Share queryShareByRID(@Param("RID") int RID);
}
