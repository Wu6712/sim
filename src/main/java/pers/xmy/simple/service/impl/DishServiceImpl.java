package pers.xmy.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xmy.simple.entity.Dish;
import pers.xmy.simple.mapper.DishMapper;
import pers.xmy.simple.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2023-06-20
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public IPage<Dish> findList(Integer pageNum, Integer pageSize) {
        return this.page(new Page<>(pageNum,pageSize));
    }

    @Override
    public IPage<Dish> findList2(Integer pageNum, Integer pageSize) {
        Page<Dish> page = new Page<>(pageNum, pageSize);
        IPage<Dish> iPage = dishMapper.selectPage(page, null);
        return iPage;
    }

    @Override
    public IPage<Dish> findListByMenuId(Integer pageNum, Integer pageSize, Integer id) {
        return this.page(new Page<>(pageNum,pageSize),new QueryWrapper<Dish>().eq("menu_id",id));
    }

    @Override
    public List<Dish> findPage5() {
        return dishMapper.selectPage2();
    }

    @Override
    public List<Dish> findPageById(Integer id) {
        return dishMapper.selectPageById(id);
    }

    @Override
    public List<Dish> findListById(Integer id) {
        return dishMapper.selectListById(id);
    }

    @Override
    public Integer collect(Integer id) {
        return dishMapper.collect(id);
    }

    @Override
    public Integer cancelCollect(Integer id) {
        return dishMapper.cancelCollect(id);
    }

    @Override
    public List<Dish> selectCollect() {
        return dishMapper.selectCollect();
    }

    @Override
    public int insertDish(Dish dish) {
        return dishMapper.insertDish(dish);
    }
}
