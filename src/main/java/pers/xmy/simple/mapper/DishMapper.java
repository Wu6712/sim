package pers.xmy.simple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.xmy.simple.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2023-06-20
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    @Select("SELECT * FROM dish ORDER BY sum DESC LIMIT 5")
    List<Dish> selectPage2();

    @Select("select * from dish where menu_id = #{id}")
    List<Dish> selectPageById(Integer id);

    @Select("select * from dish where id = #{id}")
    List<Dish> selectListById(Integer id);

    @Update("update dish set is_collect = 1 where id = #{id}")
    Integer collect(Integer id);

    @Update("update dish set is_collect = 0 where id = #{id}")
    Integer cancelCollect(Integer id);

    @Select("select * from dish where is_collect = 1")
    List<Dish> selectCollect();

    int insertDish(Dish dish);

}
