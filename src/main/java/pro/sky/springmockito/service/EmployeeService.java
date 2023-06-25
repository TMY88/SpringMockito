package pro.sky.springmockito.service;


import pro.sky.springmockito.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, Integer salary, Integer department);

    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);

    Map<String, Employee> getAllEmployees();

}
