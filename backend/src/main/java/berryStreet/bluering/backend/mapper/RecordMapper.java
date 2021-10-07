package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.Share;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
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


    @Select("select * from record where userID = #{UID}")
    public List<Record> queryRecordByUID(@Param("UID") int UID);


    @Insert("insert into record(rDate,quizContent,savedReflection,rFeedback,rTopic,userID) " +
            "values(#{record.rDate},#{record.quizContent},#{record" +
            ".savedReflection},#{record.rFeedback},#{record.rTopic},#{record.userID})")
    @Options(useGeneratedKeys = true, keyProperty = "RID", keyColumn = "RID")
    public int insertRecord(@Param("record") Record record);

    @Update("update record set rDate = #{record.rDate},quizContent =  " +
            "#{record.quizContent},savedReflection = #{record.savedReflection},rFeedback = " +
            "#{record.rFeedback},rTopic = #{record.rTopic},userID=#{record.userID} where RID = " +
            "#{RID}")
    public int updateRecord(@Param("record") Record record, @Param("RID") int RID);

    @Select("select RID from record where userID = #{userID} and rTopic = #{rTopic} and rDate = " +
            "#{rDate}")
    public int queryRID(@Param("userID") int userID, @Param("rTopic") String rTopic,
                        @Param("rDate") Date rDate);
}
