package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.Experience;
import berryStreet.bluering.backend.mapper.UserExpMapper;
import berryStreet.bluering.backend.service.UserExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserExpServiceImp implements UserExpService {

    @Autowired
    private UserExpMapper userExpMapper;

    @Override
    public List<Experience> queryUserExpList() {
        return userExpMapper.queryUserExpList();
    }

    @Override
    public int insertUserExp(String eContent) {
        DateTimeFormatter dateForm=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date= LocalDate.now().format(dateForm);
        Experience exp=Experience.builder().eContent(eContent).eDate(date).build();
        return userExpMapper.insertUserExp(exp);
    }
}
