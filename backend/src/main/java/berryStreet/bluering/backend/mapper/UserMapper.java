package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public User login(User u);

}
