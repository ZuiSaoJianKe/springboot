package cn.cloudStream.learn.controller;

import cn.cloudStream.learn.common.BaseContext;
import cn.cloudStream.learn.common.R;
import cn.cloudStream.learn.entity.Employee;
import cn.cloudStream.learn.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        employee.setPassword(password);
        Employee emp = employeeService.check(employee);
        if (emp == null) {
            return R.error("用户不存在或者密码不正确，登录失败");
        }
        if(emp.getStatus()==0){
            return R.error("用户被禁用");
        }
        request.getSession().setAttribute("employee", emp.getId());
//        获取线程的id
        Long id =Thread.currentThread().getId();
        log.info("login方法现在线程为：{}",id);
        log.info("sessionId is {}", BaseContext.getCurrentId());
        return new R(1, emp);
    }


    @PostMapping("/logout")
    public R logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
//        log.info("sessionId is {}",request.getSession().getAttribute("sessionId"));
        return new R(1, "成功");
    }


    @PostMapping
    public R<String> save(@RequestBody Employee employee, HttpServletRequest request) {
        String password;
//        BaseContext.setCurrentId(request.getSession().getAttribute("employee"));
//        System.out.println(BaseContext.getCurrentId());
        if (employee.getPassword() != null) {
            password = employee.getPassword();
        } else {
            password = "123456";
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        employee.setPassword(password);
        employeeService.addEmpolyee(employee);
//        if(!employeeService.addEmpolyee(employee)){
//            return R.error("新增失败，用户已存在或非法添加");
//        }
        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R page(int page, int pageSize, String name) {
        return new R(1, "查询成功", employeeService.getPageData(page, pageSize, name));
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        return R.success(employeeService.getById(id));
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());

        long id = Thread.currentThread().getId();
        //Long empId = (Long)request.getSession().getAttribute("employee");
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser(empId);
        log.info("employee更新方法现在线程为：{}",id);
        log.info("sessionId is {}", BaseContext.getCurrentId());
        if (employeeService.updateEmployee(employee)){
            return R.success("员工信息修改成功");
        }
        return R.error("员工信息修改失败");

    }
}