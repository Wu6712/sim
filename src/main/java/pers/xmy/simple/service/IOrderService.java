package pers.xmy.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.xmy.simple.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2023-08-05
 */
public interface IOrderService extends IService<Orders> {

    IPage<Orders> findList(Integer pageNum, Integer pageSize);

    IPage<Orders> getList(Integer pageNum, Integer pageSize);
}
