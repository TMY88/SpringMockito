package pro.sky.springmockito.service;

import org.springframework.stereotype.Service;
import pro.sky.springmockito.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @Override
    public Map<Integer, List<Employee>> getGroupsByDepartment(Integer departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> departmentId == null || employee.getDepartment().equals(departmentId))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Employee getEmployeeMaxSalary(Integer departmentId) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.getDepartment().equals(departmentId))
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeMinSalary(Integer departmentId) {
        return  employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.getDepartment().equals(departmentId))
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElse(null);
    }

    @Override
    public int getEmployeeSumSalary(Integer departmentId) {
        int sum = 0;
        for (Employee employee : employeeService.getAllEmployees().values()) {
            if (employee.getDepartment().equals(departmentId)) {
                sum += employee.getSalary();
            }
        }
        return sum;
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getAllEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
