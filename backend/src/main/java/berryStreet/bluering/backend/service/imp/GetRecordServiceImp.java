package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.SharedRecord;
import berryStreet.bluering.backend.mapper.RecordMapper;
import berryStreet.bluering.backend.service.GetRecordService;
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


}
