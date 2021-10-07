package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.Share;

import java.util.Date;
import java.util.List;

public interface GetRecordService {
    public List<Share> querySharedList(int UID);

    public Record queryRecordbySID(int SID);

    public List<Record> queryRecordByUID(int UID);

    public int queryRID(int userID, String rTopic, Date rDate);
}
