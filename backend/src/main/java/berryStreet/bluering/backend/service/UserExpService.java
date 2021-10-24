package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.Experience;

import java.util.List;

public interface UserExpService {
    public List<Experience> queryUserExpList();
    public int insertUserExp(String eContent);
}
