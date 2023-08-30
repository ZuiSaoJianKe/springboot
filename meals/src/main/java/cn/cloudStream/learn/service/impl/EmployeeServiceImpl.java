package cn.cloudStream.learn.service.impl;

import cn.cloudStream.learn.entity.Employee;
import cn.cloudStream.learn.mapper.EmployeeMapper;
import cn.cloudStream.learn.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService  {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee check(Employee employee) {
        Employee emp=employeeMapper.findEmployeeByName(employee.getUsername());
        if (emp != null && emp.getPassword().equals(employee.getPassword())) {
            return emp;
        }
        return null;
    }

    @Override
    public boolean addEmpolyee(Employee employee) {
        return employeeMapper.insert(employee)>0;
    }

    @Override
    public Map getPageData(int pageNum, int pageSize, String keyword) {
        LambdaQueryWrapper<Employee> lqw=new LambdaQueryWrapper<>();
        lqw.like(keyword!=null,Employee::getName,keyword);
        IPage<Employee> page=new Page<>(pageNum,pageSize);
        employeeMapper.selectPage(page,lqw);
        List<Employee> empData=page.getRecords();
        long total=  page.getTotal();
        Map empMap=new HashMap<>();
        empMap.put("total",total);
        empMap.put("records",empData);
        return empMap;
    }

    @Override
    public Employee getById(long id) {
        Employee employee=employeeMapper.getById(id);
        if (employee!=null){
            return employee;
        }
        return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        log.info("启动更新");
        return employeeMapper.updateById(employee)>0;
    }
}
