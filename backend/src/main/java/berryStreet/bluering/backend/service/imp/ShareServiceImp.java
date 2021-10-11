package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.Share;
import berryStreet.bluering.backend.entity.ShareVO;
import berryStreet.bluering.backend.mapper.ShareMapper;
import berryStreet.bluering.backend.mapper.UserMapper;
import berryStreet.bluering.backend.service.GetRecordService;
import berryStreet.bluering.backend.service.SetRecordService;
import berryStreet.bluering.backend.service.ShareService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShareServiceImp implements ShareService {
    @Autowired
    ShareMapper shareMapper;
    @Autowired
    SetRecordService setRecordService;
    @Autowired
    GetRecordService getRecordService;
    @Autowired
    UserMapper userMapper;

    @Override
    public int saveShare(int RID, ShareVO shareVO) {
        if (RID <= 0 && RID != -1) {
            return Constant.SAVE_FAIL;
        }
        String quizContent = JSON.toJSONString(shareVO.getQuizContent());
        LocalDate date = LocalDate.now();
        Record record = Record.builder()
                .quizContent(quizContent)
                .savedReflection(shareVO.getShareReflection())
                .rFeedback(shareVO.getFeedback())
                .rTopic(shareVO.getTopic())
                .rDate(date)
                .userID(shareVO.getUserID())
                .build();
        int receiver = userMapper.queryUserByEmail(shareVO.getReceiver()).getUID();
        if (receiver == 0) {
            return Constant.SAVE_FAIL;
        }
        Share share = Share.builder()
                .sender(shareVO.getSender())
                .shareReflection(shareVO.getShareReflection())
                .receiver(receiver)
                .build();
        if (RID == -1) {
            if (setRecordService.saveRecord(record) == 1) {
                share.setRecordID(getRecordService.queryRID(shareVO.getUserID(),
                        shareVO.getTopic(), date));
                if (shareMapper.insertShare(share) == 1) {
                    return Constant.SAVE_SUCCESS;
                }
            }
        } else {
            if (shareMapper.updateShare(share, RID) == 1) {
                return Constant.SAVE_SUCCESS;
            }
        }
        return Constant.SAVE_FAIL;
    }
}
