package pro.sky.springmockito.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.springmockito.exceptions.EmployeeAlreadyAddedException;
import pro.sky.springmockito.exceptions.EmployeeNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();


    @Test
    public void shouldGetExceptionWhenRepeatedAdded() {
        String firstName = "Ivan";
        String lastName = "Ivanov";
        int salary = 100000;
        int department = 1;

        out.addEmployee(firstName, lastName, salary, department);

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(firstName, lastName, salary, department));

    }

    @Test
    public void shouldGetExceptWhenFindingOrRemovingNotExistingEmployee(){
        String surname = "Mikhail";
        String name = "Mikhailov";

        Assertions.assertThrows(EmployeeNotFoundException.class, ()->out.findEmployee( name,surname));
        Assertions.assertThrows(EmployeeNotFoundException.class, ()->out.removeEmployee(name,surname));
    }

}