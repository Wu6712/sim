package pers.xmy.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.xmy.simple.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2023-06-20
 */
public interface IDishService extends IService<Dish> {

    IPage<Dish> findList(Integer pageNum, Integer pageSize);

    IPage<Dish> findList2(Integer pageNum, Integer pageSize);

    IPage<Dish> findListByMenuId(Integer pageNum,Integer pageSize,Integer Id);

    List<Dish> findPage5();

    List<Dish> findPageById(Integer id);

    List<Dish> findListById(Integer id);

    Integer collect(Integer id);

    Integer cancelCollect(Integer id);

    List<Dish> selectCollect();

    int insertDish(Dish dish);
}
