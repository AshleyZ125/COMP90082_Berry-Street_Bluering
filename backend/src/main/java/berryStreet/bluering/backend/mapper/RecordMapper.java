package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.Share;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecordMapper {

    @Select("select s.SID SID, r.rDate rDate, r.quizTopic quizTopic, " +
            "CASE WHEN r.userID=-1 THEN 'Anonymous' ELSE u.username END username " +
            "from share s inner join record r inner join user u " +
            "on s.recordID=r.RID and (r.userID=u.UID or r.userID=-1)" +
            "where s.receiver=#{UID}")
    public List<Share> querySharedList(@Param("UID") int UID);

    @Select("select r.RID RID, r.quizContent quizContent, r.feedback feedback, s.shareReflection savedReflection " +
            "from share s inner join record r " +
            "on s.recordID=r.RID where s.SID=#{SID}")
    public Record queryRecordbySID(@Param("SID") int SID);
}
