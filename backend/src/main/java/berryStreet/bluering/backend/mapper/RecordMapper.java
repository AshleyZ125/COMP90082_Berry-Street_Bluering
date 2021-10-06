package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.SharedRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecordMapper {

    @Select("select s.SID SID, r.rTopic rTopic, r.rDate rDate,CASE WHEN r.userID=0 THEN 'Anonymous' ELSE (" +
            "select username from user where UID=r.userID) END username " +
            "from share s inner join record r " +
            "on s.recordID=r.RID where s.receiver=#{UID}")
    public List<SharedRecord> querySharedList(@Param("UID") int UID);

    @Select("select r.RID RID, r.quizContent quizContent, s.shareReflection savedReflection," +
            " r.rFeedback rFeedback, r.rTopic rTopic, r.userID userID, r.rDate rDate  " +
            "from share s inner join record r " +
            "on s.recordID=r.RID where s.SID=#{SID}")
    public Record queryRecordbySID(@Param("SID") int SID);
}
