package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.SharedRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
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
                        @Param("rDate") LocalDate rDate);

    @Select("select * from record where RID = #{RID}")
    public Record queryRecordByRID(@Param("RID") int RID);
}
