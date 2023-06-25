package pro.sky.springmockito.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.springmockito.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final List<Employee> employees = new ArrayList<>() {{
        add(new Employee("Ivan", "Ivanov", 100000, 1));
        add(new Employee("Dmitry", "Axenov", 120000, 1));
        add(new Employee("Mikhail", "Ponomarev", 85000, 3));
    }};

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnNullWhenNoEmployeeInDepartment() {
        final int departmentId = 2;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirsName() + employee.getLastName(), employee);
        }

        when(employeeService.getAllEmployees()).thenReturn(employeeMap);


        Employee employeeMaxSalary = departmentService.getEmployeeMaxSalary(departmentId);

        Assertions.assertEquals(employees.get(1),employeeMaxSalary);
    }

    @Test
    void shouldReturnEmployeeWithMaxSalary() {
        //given
        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirsName() + employee.getLastName(), employee);
        }

        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        Employee employeeMaxSalary = departmentService.getEmployeeMaxSalary(departmentId);

        //then
        Assertions.assertEquals(employees.get(1),employeeMaxSalary);

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void shouldReturnEmployeeWithMinSalary() {
        //given
        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirsName() + employee.getLastName(), employee);
        }

        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        Employee employeeMinSalary = departmentService.getEmployeeMinSalary(departmentId);

        //then
        Assertions.assertEquals(employees.get(0),employeeMinSalary);

        verify(employeeService, times(1)).getAllEmployees();
    }
    @Test
    void shouldReturnEmployeesSumSalary() {
        //given
        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            employeeMap.put(employee.getFirsName() + employee.getLastName(), employee);
        }

        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        int employeeSumSalary = departmentService.getEmployeeSumSalary(departmentId);

        //then
        Assertions.assertEquals(220000,employeeSumSalary);

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void shouldReturnGroupsByDepartment() {
        //given
        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
            if (employee.getDepartment()==1) {
                employeeMap.put(String.valueOf(employee.getDepartment()), employee);
            }
        }
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        Map employeesGroupedByDepartment = departmentService.getGroupsByDepartment(departmentId);

        //then
        Assertions.assertEquals(employeeMap,employeesGroupedByDepartment);

        verify(employeeService, times(1)).getAllEmployees();
    }
    @Test
    void shouldReturnEmployeesByDepartment() {
        //given
        final int departmentId = 1;
        final Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employees) {
                employeeMap.put(String.valueOf(employee.getDepartment()), employee);
        }
        when(employeeService.getAllEmployees()).thenReturn(employeeMap);

        //when
        Map employeesByDepartment = departmentService.getEmployeesByDepartment();

        //then
        Assertions.assertEquals(employeeMap,employeesByDepartment);

        verify(employeeService, times(1)).getAllEmployees();
    }
}