package pro.sky.springmockito.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.springmockito.Employee;
import pro.sky.springmockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public Map<Integer, List<Employee>> getGroupsByDepartment(@PathVariable Integer id) {
        return departmentService.getGroupsByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public Employee getEmployeeMaxSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getEmployeeMinSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeMinSalary(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getEmployeeSumSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeSumSalary(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return departmentService.getEmployeesByDepartment();
    }
}

