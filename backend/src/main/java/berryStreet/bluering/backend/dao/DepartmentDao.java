package berryStreet.bluering.backend.dao;

import berryStreet.bluering.backend.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepartmentDao {
    @Select("select * from department")
    List<Department> getDepartments();
    @Select("select * from department where id = #{id}")
    Department getDepartment(Integer id);
}
