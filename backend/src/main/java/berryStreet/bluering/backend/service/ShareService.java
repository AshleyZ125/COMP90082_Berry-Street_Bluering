package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.ShareVO;

public interface ShareService {
    public Response<Integer> saveShare(int RID, ShareVO shareVO);
}
