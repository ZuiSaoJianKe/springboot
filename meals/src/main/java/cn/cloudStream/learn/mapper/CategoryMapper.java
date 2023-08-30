package cn.cloudStream.learn.mapper;

import cn.cloudStream.learn.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("select * from category where id =#{id}")
    Category findCategoryById(Long id);
}
