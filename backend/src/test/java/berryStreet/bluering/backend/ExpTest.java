package berryStreet.bluering.backend;

import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.controller.UserExpController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpTest {
    @Autowired
    UserExpController userExpController;

    @Test
    public void expListTest(){
        AjaxResult result=userExpController.expList();
        System.out.println(result);
    }

    @Test
    public void addUserExpTest(){
        String content="good app!!!";
        AjaxResult result=userExpController.addUserExp(content);
        System.out.println(result);
        content="";
        result=userExpController.addUserExp(content);
        System.out.println(result);

    }
}
