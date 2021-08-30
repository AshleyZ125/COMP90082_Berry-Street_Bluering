package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.Department;
import berryStreet.bluering.backend.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImp {
    @Autowired
    private DeptMapper deptMapper;

    public List<Department> selectDeptList() {
        return deptMapper.selectDeptList();
    }


    public Department selectDeptById(Integer id) {
        return deptMapper.selectDeptById(id);
    }
}
