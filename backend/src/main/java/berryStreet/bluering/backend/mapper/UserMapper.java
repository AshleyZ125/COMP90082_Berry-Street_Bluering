package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.User;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Mapper;
=======
import org.apache.ibatis.annotations.*;
>>>>>>> origin/20210901-backend-dev
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    public User login(User u);

    @Select("select * from user where email = #{email}")
    public User queryUserByEmail(@Param("email") String email);

    @Select("select * from user where username = #{username}")
    public User queryUserByUsername(@Param("username") String username);

    @Insert("insert into `user` values(#{user.UID},#{user.email},#{user.username},#{user.password},#{user.role})")
    @Options(useGeneratedKeys = true, keyProperty = "user.UID", keyColumn = "UID")
    public int insertUser(@Param("user") User user);


}
