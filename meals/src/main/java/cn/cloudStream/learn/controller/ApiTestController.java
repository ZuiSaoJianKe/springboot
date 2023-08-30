package cn.cloudStream.learn.controller;

import cn.cloudStream.learn.common.R;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController   //为Response+Controller结合，其中Response告诉接口返回json数据,如果没有数据返回形式非map，按照text形式返回数据
@RequestMapping("/test")  //规定统一前缀
public class ApiTestController {
    Map<Object,Object> map = new HashMap<>();
    @GetMapping("/testGet")  //GetMapping定义方法为get请求，路由为/test/testGet
    public R getMethod(int page, int pageSize, String name){
        //定义传参为page、pageSize和name为该接口的传参请求
        map.put("page",page);
        map.put("pageSize",pageSize);
        map.put("name",name);
        return new R(1,"成功",map);
//      return  name;
    }

    @PostMapping("/testPost") //PostMapping定义方法为post请求，路由为/test/testPost
    public R postMethod(@RequestBody Map<Object,Object> map){
        return R.success(map);
    }

    @PostMapping("/testPost/{id}") //PostMapping定义方法为post请求，路由为/test/testPost/路径
    public R postPathVariable(@PathVariable int id){
        map.put("id",id);
        return new R(1,map);
    }
}
