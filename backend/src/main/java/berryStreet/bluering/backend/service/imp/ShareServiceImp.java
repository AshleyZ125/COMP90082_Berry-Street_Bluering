package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.RecordVO;
import berryStreet.bluering.backend.entity.Share;
import berryStreet.bluering.backend.mapper.ShareMapper;
import berryStreet.bluering.backend.service.GetRecordService;
import berryStreet.bluering.backend.service.SetRecordService;
import berryStreet.bluering.backend.service.ShareService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImp implements ShareService {
    @Autowired
    ShareMapper shareMapper;
    @Autowired
    SetRecordService setRecordService;
    @Autowired
    GetRecordService getRecordService;

    @Override
    public int saveShare(Share share, int RID, RecordVO recordVO) {
        if (RID <= 0 && RID != -1) {
            return Constant.SAVE_FAIL;
        }
        if (RID == -1) {
            String quizContent = JSON.toJSONString(recordVO.getQuizContent());
            Record record = Record.builder()
                    .quizContent(quizContent)
                    .savedReflection(recordVO.getSavedReflection())
                    .rFeedback(recordVO.getRFeedback())
                    .rTopic(recordVO.getRTopic())
                    .rDate(recordVO.getRDate())
                    .userID(recordVO.getUserID())
                    .build();
            if (setRecordService.saveRecord(record) == 1) {
                share.setRecordID(getRecordService.queryRID(recordVO.getUserID(),
                        recordVO.getRTopic(),recordVO.getRDate()));
                if (shareMapper.insertShare(share) == 1) {
                    return Constant.SAVE_SUCCESS;
                }
            }
        } else {
            if (shareMapper.updateShare(share,RID) == 1) {
                return Constant.SAVE_SUCCESS;
            }
        }
        return Constant.SAVE_FAIL;
    }
}
