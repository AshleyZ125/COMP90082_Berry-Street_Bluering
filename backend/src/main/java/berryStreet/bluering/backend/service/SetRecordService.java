package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Record;

public interface SetRecordService {
    public int saveRecord(Record record);
    public int updateRecord(Record record,int RID);
}
