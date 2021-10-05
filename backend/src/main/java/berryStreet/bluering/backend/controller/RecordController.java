package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.QuizSelection;
import berryStreet.bluering.backend.entity.Record;
import berryStreet.bluering.backend.entity.RecordVO;
import berryStreet.bluering.backend.entity.Share;
import berryStreet.bluering.backend.service.GetRecordService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RecordController {
    @Autowired
    private GetRecordService getRecordService;

    @GetMapping("/api/record/sharedList/{UID}")
    public AjaxResult sharedList(@PathVariable("UID") int superID){
        if(superID<=0){
            return AjaxResult.error("Input empty!");
        }
        List<Share> sharedList=getRecordService.querySharedList(superID);
        if (sharedList.isEmpty()){
            return AjaxResult.warn("No LEC feedback now.");
        }
        return AjaxResult.success(sharedList);
    }

    @GetMapping("/api/record/getSharedFeedback/{SID}")
    public AjaxResult getSharedFeedback(@PathVariable("SID") int SID){
        if(SID<=0){
            return AjaxResult.error("Input empty!");
        }
        Record feedback=getRecordService.queryRecordbySID(SID);
        List<QuizSelection> quizSelections = JSON.parseObject(feedback.getQuizContent(),
                new TypeReference<List<QuizSelection>>() {
                }.getType());
        RecordVO result= RecordVO.builder().RID(feedback.getRID())
                .quizContent(quizSelections)
                .feedback(feedback.getFeedback())
                .savedReflection(feedback.getSavedReflection())
                .build();
        return AjaxResult.success(result);
    }
}
