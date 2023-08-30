package cn.cloudStream.learn.mapper;

import cn.cloudStream.learn.entity.Employee;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class EmployeeMapperTestcase {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    void testFindEmployeeByUsername(){
        System.out.println(employeeMapper.getById(1L));
    }

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void testReidsLinkTest(){
        System.out.println("启动测试类方法..objectShow.............");
        ValueOperations objOps = redisTemplate.opsForValue();
        Employee employee=new Employee();
        employee.setId(1L);
        employee.setName("周瑜");
        String emp = JSON.toJSONString(employee);
        objOps.set("userkey", emp);
        System.out.println("-----获得对象为："+objOps.get("userkey").toString());
//        redisTemplate.delete("userkey");
    }
}

