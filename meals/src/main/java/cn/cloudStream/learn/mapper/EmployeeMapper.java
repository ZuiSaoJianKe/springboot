package cn.cloudStream.learn.mapper;

import cn.cloudStream.learn.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select("select * from employee where username =#{username}")
    Employee findEmployeeByName(String username);
    @Select("select * from employee where id =#{id}")
    Employee getById(Long id);
}
