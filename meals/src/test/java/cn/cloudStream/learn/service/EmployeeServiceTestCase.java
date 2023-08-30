package cn.cloudStream.learn.service;

import cn.cloudStream.learn.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTestCase {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCheck() {
        Employee employee = new Employee();
        employee.setUsername("admin");
        employee.setPassword("123456");
        System.out.println(employee.getUsername());
        System.out.println(employeeService.check(employee));
    }
}
