package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.mapper.RecordMapper;
import berryStreet.bluering.backend.service.SetRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetRecordServiceImp implements SetRecordService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public int saveRecord(Record record) {
        return recordMapper.insertRecord(record);
    }

    @Override
    public int updateRecord(Record record, int RID) {
        return recordMapper.updateRecord(record, RID);
    }
}
