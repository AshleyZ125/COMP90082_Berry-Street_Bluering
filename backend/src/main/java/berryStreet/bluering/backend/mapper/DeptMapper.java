package berryStreet.bluering.backend.mapper;

import berryStreet.bluering.backend.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeptMapper {
    //@Select("select * from department")
    List<Department> selectDeptList();
    //@Select("select * from department where id = #{id}")
    Department selectDeptById(Integer id);
}
