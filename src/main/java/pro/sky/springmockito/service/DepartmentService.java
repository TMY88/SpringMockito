package pro.sky.springmockito.service;


import pro.sky.springmockito.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<Integer, List<Employee>> getGroupsByDepartment(Integer departmentId);
    Employee getEmployeeMaxSalary(Integer departmentId);

    Employee getEmployeeMinSalary(Integer departmentId);

    int getEmployeeSumSalary(Integer departmentId);

    Map<Integer, List<Employee>> getEmployeesByDepartment();


}
