package berryStreet.bluering.backend;

import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.controller.UserExpController;
import berryStreet.bluering.backend.entity.Experience;
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
        Experience exp=Experience.builder().build();
        exp.setEContent("good app!!!");
        AjaxResult result=userExpController.addUserExp(exp);
        System.out.println(result);
        exp.setEContent("");
        result=userExpController.addUserExp(exp);
        System.out.println(result);

    }
}
