package berryStreet.bluering.backend;

import berryStreet.bluering.backend.Utils.MailUtil;
import berryStreet.bluering.backend.controller.RecordController;
import berryStreet.bluering.backend.entity.Mail;
import berryStreet.bluering.backend.entity.QuizSelection;
import berryStreet.bluering.backend.entity.RecordVO;
import berryStreet.bluering.backend.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class RecordTest {
    @Autowired
    RecordController recordController;
    @Autowired
    MailUtil mailUtil;
    @Test
    public void sharedListTest(){
        int superID=1;
        recordController.sharedList(superID);
    }

    @Test
    public void getSharedFeedbackTest() {
        int SID = 1;
        recordController.sharedList(SID);
    }

    @Test
    public void saveShareTest() {
        Share share = Share.builder()
                .sender(2)
                .receiver(1)
                .shareReflection("asdasd")
                .build();
        List<QuizSelection> quizSelections = new ArrayList<>();
        quizSelections.add(QuizSelection.builder()
                .option("value")
                .content("sadasd")
                .build());
        RecordVO recordVO = RecordVO.builder()
                .quizContent(quizSelections)
                .feedback("asdasd")
                .topic("asdasd")
                .savedReflection("sad")
                .userID(2)
                .build();
//        recordController.saveShare(-1,share,recordVO);
//        recordController.saveShare(10,share,recordVO);
    }
    @Test void saveRecord(){
        List<QuizSelection> quizSelections = new ArrayList<>();
        quizSelections.add(QuizSelection.builder()
                .option("value")
                .content("ss")
                .build());
        RecordVO recordVO = RecordVO.builder()
                .quizContent(quizSelections)
                .feedback("123sasd")
                .topic("a123")
                .savedReflection("sad")
                .userID(3)
                .build();
//        recordController.saveRecord(-1,recordVO);
//        recordController.saveRecord(13,recordVO);
    }

    @Test
    public void getRecord(){
        System.out.println(recordController.getRecord(16));
    }

    @Test
    public void mail(){
        Mail mail = Mail.builder()
                .from("498295243@qq.com")
                .to("498295243@qq.com")
                .sentDate(new Date())
                .subject("test")
                .text("test")
                .build();
        boolean success = mailUtil.sendMail(mail);
        System.out.println(success);
    }

}
