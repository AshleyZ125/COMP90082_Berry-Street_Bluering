package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.RecordVO;
import berryStreet.bluering.backend.entity.Share;

public interface ShareService {
    public int saveShare(Share share, int RID, RecordVO recordVO);
}
