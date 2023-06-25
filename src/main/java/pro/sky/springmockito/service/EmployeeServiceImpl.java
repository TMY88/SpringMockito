package pro.sky.springmockito.service;

import org.springframework.stereotype.Service;
import pro.sky.springmockito.Employee;
import pro.sky.springmockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.springmockito.exceptions.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();;



    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) {
        String employeeKey = getEmployeeKey(firstName, lastName);
        if (employees.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже есть");
        }
        employees.put(employeeKey, new Employee(firstName, lastName, salary, department));
        return employees.get(employeeKey);

        //throw new EmployeeStorageIsFullException();
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String employeeKey = getEmployeeKey(firstName, lastName);
        if (!employees.containsKey(employeeKey)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет!");
        }
        employees.remove(employeeKey);
        return null;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String employeeKey = getEmployeeKey(firstName, lastName);
        if (employees.containsKey(employeeKey)) {
            return employees.get(employeeKey);
        } else {
            throw new EmployeeNotFoundException("Такого сотрудника нет!");
        }
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return employees;
    }

    public String getEmployeeKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
