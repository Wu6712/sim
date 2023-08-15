package pers.xmy.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.xmy.simple.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2023-06-20
 */
public interface IMenuService extends IService<Menu> {

    IPage<Menu> findList(Integer pageNum, Integer pageSize);
}
