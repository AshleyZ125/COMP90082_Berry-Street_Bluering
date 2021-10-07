package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.SharedRecord;

import java.util.List;

public interface GetRecordService {
    public List<SharedRecord> querySharedList(int UID);
    public Record queryRecordbySID(int SID);
}
