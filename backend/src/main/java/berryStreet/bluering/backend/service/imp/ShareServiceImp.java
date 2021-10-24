package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.Share;
import berryStreet.bluering.backend.entity.ShareVO;
import berryStreet.bluering.backend.mapper.ShareMapper;
import berryStreet.bluering.backend.mapper.UserMapper;
import berryStreet.bluering.backend.service.GetRecordService;
import berryStreet.bluering.backend.service.Response;
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
    public Response<Integer> saveShare(int RID, ShareVO shareVO) {
        if (RID <= 0 && RID != -1) {
            return new Response<>(Constant.SAVE_FAIL, -1);
        }
        String quizContent = JSON.toJSONString(shareVO.getQuizContent());
        LocalDate date = LocalDate.now();
        Record record = Record.builder()
                .quizContent(quizContent)
                .rFeedback(shareVO.getFeedback())
                .rTopic(shareVO.getTopic())
                .rDate(date)
                .userID(shareVO.getUserID())
                .build();
        int receiver = userMapper.queryUserByEmail(shareVO.getReceiver()).getUID();
        if (receiver == 0) {
            return new Response<>(Constant.SAVE_FAIL, -1);
        }
        Share share = Share.builder()
                .sender(shareVO.getSender())
                .shareReflection(shareVO.getShareReflection())
                .receiver(receiver)
                .build();
        if (RID == -1) {
            int res = setRecordService.saveRecord(record);
            if (res == 1) {
                share.setRecordID(record.getRID());
                if (shareMapper.insertShare(share) == 1) {
                    return new Response<Integer>(Constant.SAVE_SUCCESS, record.getRID());
                }
            }
        } else {
            share.setRecordID(RID);
            if (shareMapper.insertShare(share) == 1) {
                return new Response<Integer>(Constant.SAVE_SUCCESS, RID);
            }
        }
        return new Response<>(Constant.SAVE_FAIL, -1);
    }
}
