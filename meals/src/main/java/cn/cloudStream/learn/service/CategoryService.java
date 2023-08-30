package cn.cloudStream.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.cloudStream.learn.entity.Category;

public interface CategoryService extends IService<Category> {



    Category check(Category category);
    public void remove(Long id);

}
