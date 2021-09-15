package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select UID,email,username from user where email = #{u.email} and password=#{u.password} and role=#{u.role}")
    public User checkUserExist(@Param("u") User user);
    //public User checkUserExist(User u);

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
    public int resetPassword(@Param("UID") int UID, @Param("password") String password);

    @Update("update user set email = #{email} where UID = #{UID}")
    public int resetEmail(@Param("UID") int UID, @Param("email") String email);

    @Delete("delete from user where UID = #{UID}")
    public int deleteUserByUID(@Param("UID") int UID);
}
