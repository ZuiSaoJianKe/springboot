package cn.cloudStream.learn.service.impl;

import cn.cloudStream.learn.entity.Dish;
import cn.cloudStream.learn.mapper.DishMapper;
import cn.cloudStream.learn.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
