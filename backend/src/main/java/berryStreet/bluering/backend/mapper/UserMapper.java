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

    @Select("select * from user where UID = #{UID}")
    public User queryUserByUID(@Param("UID") int UID);

    @Insert("insert into `user` values(#{user.UID},#{user.email},#{user.username},#{user.password},#{user.role})")
    @Options(useGeneratedKeys = true, keyProperty = "user.UID", keyColumn = "UID")
    public int insertUser(@Param("user") User user);

    @Update("update user set password = #{password} where UID = #{UID}")
    public boolean resetPassword(@Param("password") String password, @Param("UID") int UID);

    @Update("update user set email = #{email} where UID = #{UID}")
    public boolean resetEmail(@Param("email") String email, @Param("UID") int UID);

    @Delete("delete from user where UID = #{UID}")
    public boolean deleteUserByUID(@Param("UID") int UID);
}
