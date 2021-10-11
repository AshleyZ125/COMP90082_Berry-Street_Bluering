package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.*;
import berryStreet.bluering.backend.service.GetRecordService;
import berryStreet.bluering.backend.service.SetRecordService;
import berryStreet.bluering.backend.service.ShareService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
public class RecordController {
    @Autowired
    private GetRecordService getRecordService;
    @Autowired
    private SetRecordService setRecordService;
    @Autowired
    private ShareService shareService;

    @GetMapping("/api/record/sharedList/{UID}")
    public AjaxResult sharedList(@PathVariable("UID") int superID) {
        if (superID <= 0) {
            return AjaxResult.error("Input empty!");
        }
        List<SharedRecord> sharedList = getRecordService.querySharedList(superID);
        if (sharedList.isEmpty()) {
            return AjaxResult.warn("No LEC feedback now.");
        }
        return AjaxResult.success(sharedList);
    }

    @GetMapping("/api/record/getSharedFeedback/{SID}")
    public AjaxResult getSharedFeedback(@PathVariable("SID") int SID) {
        if (SID <= 0) {
            return AjaxResult.error("Input empty!");
        }
        Record feedback = getRecordService.queryRecordbySID(SID);
        if (feedback.getRID() == 0)
            return AjaxResult.warn("This shared record is not exist.");
        List<QuizSelection> quizSelections = JSON.parseObject(feedback.getQuizContent(),
                new TypeReference<List<QuizSelection>>() {
                }.getType());
        RecordVO result = RecordVO.builder().RID(feedback.getRID())
                .quizContent(quizSelections)
                .rFeedback(feedback.getRFeedback())
                .savedReflection(feedback.getSavedReflection())
                .build();
        return AjaxResult.success(result);
    }

    @GetMapping("/api/record/getRecordList/{UID}")
    public AjaxResult getRecordList(@PathVariable("UID") int UID) {
        if (UID <= 0) {
            return AjaxResult.error("input empty!");
        }
        List<Record> recordList = getRecordService.queryRecordByUID(UID);
        if (null == recordList || recordList.isEmpty()) {
            return AjaxResult.warn("No Record now,may try again");
        }
        List<RecordVO> recordVOList = new LinkedList<>();
        for (Record record : recordList) {
            List<QuizSelection> quizSelections = JSON.parseObject(record.getQuizContent(),
                    new TypeReference<List<QuizSelection>>() {
                    }.getType());
            RecordVO recordVO = RecordVO.builder().RID(record.getRID())
                    .quizContent(quizSelections)
                    .rFeedback(record.getRFeedback())
                    .savedReflection(record.getSavedReflection())
                    .build();
            recordVOList.add(recordVO);
        }
        return AjaxResult.success(recordVOList);
    }

    @GetMapping("/api/record/getRecord/{RID}")
    public AjaxResult getRecord(@PathVariable("RID") int RID) {
        if (RID <= 0) {
            return AjaxResult.error("input empty!");
        }
        Record record = getRecordService.getRecordByRID(RID);
        if (null == record) {
            return AjaxResult.warn("No Record now,may try again");
        }
        List<QuizSelection> quizSelection = JSON.parseObject(record.getQuizContent(),
                new TypeReference<List<QuizSelection>>() {
                }.getType());
        RecordVO recordVO = RecordVO.builder()
                .rDate(record.getRDate())
                .rFeedback(record.getRFeedback())
                .rTopic(record.getRTopic())
                .savedReflection(record.getSavedReflection())
                .userID(record.getUserID())
                .quizContent(quizSelection)
                .build();
        return AjaxResult.success(recordVO);

    }

    @PostMapping("/api/record/saveRecord")
    public AjaxResult saveRecord(@RequestBody RecordVO recordVO) {
        System.out.println(recordVO);
//        if (RID <= 0 && RID != -1) {
//            return AjaxResult.error("input empty!");
//        }
//        Record record = null;
//        if (RID == -1) {
//            String quizContent = JSON.toJSONString(recordVO.getQuizContent());
//            record = Record.builder()
//                    .quizContent(quizContent)
//                    .savedReflection(recordVO.getSavedReflection())
//                    .rFeedback(recordVO.getRFeedback())
//                    .rTopic(recordVO.getRTopic())
//                    .rDate(LocalDate.now())
//                    .userID(recordVO.getUserID())
//                    .build();
//            int res = setRecordService.saveRecord(record);
//            if (res == 1) {
//                return AjaxResult.success(getRecordService.queryRID(record.getUserID(),
//                        record.getRTopic(),
//                        record.getRDate()));
//            } else {
//                return AjaxResult.error("not success,save again.");
//            }
//        } else {
//            String quizContent = JSON.toJSONString(recordVO.getQuizContent());
//            record = Record.builder()
//                    .quizContent(quizContent)
//                    .savedReflection(recordVO.getSavedReflection())
//                    .rFeedback(recordVO.getRFeedback())
//                    .rTopic(recordVO.getRTopic())
//                    .rDate(LocalDate.now())
//                    .userID(recordVO.getUserID())
//                    .build();
//            int res = setRecordService.updateRecord(record, RID);
//            if (res == 1) {
//                AjaxResult.success(RID);
//            } else {
//                return AjaxResult.error("not success,save again.");
//            }
//        }
//        return AjaxResult.error("not success,save again.");
        return AjaxResult.success();
    }

    @PostMapping("/api/record/saveShare/{RID}")
    public AjaxResult saveShare(@PathVariable("RID") int RID, @RequestBody Share share,
                                @RequestBody RecordVO recordVO) {
        if (shareService.saveShare(share, RID, recordVO) == Constant.SAVE_SUCCESS) {
            return AjaxResult.success(getRecordService.queryRID(share.getSender(), recordVO.getRTopic(),
                    recordVO.getRDate()));
        }
        return AjaxResult.error("not success,save again.");
    }

}
