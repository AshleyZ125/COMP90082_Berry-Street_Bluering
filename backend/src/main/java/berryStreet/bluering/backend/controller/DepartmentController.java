package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.entity.Department;
import berryStreet.bluering.backend.service.imp.DeptServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DeptServiceImp deptService;

    @GetMapping("/getDepartments")
    public List<Department> getDepartments(){
        return deptService.selectDeptList();
    }

    // 查询全部部门
    @GetMapping("/getDepartment/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return deptService.selectDeptById(id);
    }
}
