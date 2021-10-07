package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.SharedRecord;

import java.time.LocalDate;
import java.util.List;

public interface GetRecordService {
    public List<SharedRecord> querySharedList(int UID);
    public Record queryRecordbySID(int SID);

    public List<Record> queryRecordByUID(int UID);

    public int queryRID(int userID, String rTopic, LocalDate rDate);
}
