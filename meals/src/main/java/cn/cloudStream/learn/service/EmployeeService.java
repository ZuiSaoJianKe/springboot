package cn.cloudStream.learn.service;

import cn.cloudStream.learn.entity.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee check(Employee employee);
    boolean addEmpolyee(Employee employee);
    Map getPageData(int pageNum,int pageSize,String keyword);
    Employee getById(long id);
    boolean updateEmployee(Employee employee);
}
