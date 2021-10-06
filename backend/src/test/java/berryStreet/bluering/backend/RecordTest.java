package berryStreet.bluering.backend;

import berryStreet.bluering.backend.controller.RecordController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    }
}
