package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.*;
import berryStreet.bluering.backend.mapper.RecordMapper;
import berryStreet.bluering.backend.service.GetRecordService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GetRecordServiceImp implements GetRecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<SharedRecord> querySharedList(int UID) {
        return recordMapper.querySharedList(UID);
    }

    @Override
    public Record queryRecordbySID(int SID) {
        return recordMapper.queryRecordbySID(SID);
    }

    @Override
    public List<Record> queryRecordByUID(int UID) {
        return recordMapper.queryRecordByUID(UID);
    }

    @Override
    public int queryRID(int userID, String rTopic, LocalDate rDate) {
        return recordMapper.queryRID(userID, rTopic, rDate);
    }

    @Override
    public Record getRecordByRID(int RID) {
        return recordMapper.queryRecordByRID(RID);
    }


}
