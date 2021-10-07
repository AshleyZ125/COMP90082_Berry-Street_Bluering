package berryStreet.bluering.backend;

import berryStreet.bluering.backend.controller.RecordController;
import berryStreet.bluering.backend.entity.Option;
import berryStreet.bluering.backend.entity.QuizSelection;
import berryStreet.bluering.backend.entity.RecordVO;
import berryStreet.bluering.backend.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class RecordTest {
    @Autowired
    RecordController recordController;
    @Test
    public void sharedListTest(){
        int superID=1;
        recordController.sharedList(superID);
    }

    @Test
    public void getSharedFeedbackTest(){
        int SID=1;
        recordController.sharedList(SID);

    @Test
    public void saveShareTest() {
        Share share = Share.builder()
                .quizTopic("topic")
                .rDate(new Date())
                .receiver(1)
                .shareReflection("asdasd")
                .sender(5)
                .username("guan")
                .build();
        List<QuizSelection> quizSelections = new ArrayList<>();
        quizSelections.add(QuizSelection.builder()
                .option(Option.builder().key(1).point(4).value("asdasd").build())
                .qContent("sadasd")
                .build());
        RecordVO recordVO = RecordVO.builder()
                .quizContent(quizSelections)
                .feedback("asdasd")
                .quizTopic("asdasd")
                .rDate(new Date())
                .savedReflection("sad")
                .userID(1)
                .build();
        recordController.saveShare(-1, share,recordVO);
    }
}
