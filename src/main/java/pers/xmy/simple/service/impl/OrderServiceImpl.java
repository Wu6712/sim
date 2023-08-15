package pers.xmy.simple.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xmy.simple.entity.Orders;
import pers.xmy.simple.mapper.OrderMapper;
import pers.xmy.simple.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2023-08-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Orders> findList(Integer pageNum, Integer pageSize) {
        return (new Page<>(pageNum,pageSize));
    }

    @Override
    public IPage<Orders> getList(Integer pageNum, Integer pageSize) {

        Page<Orders> page = new Page<>(pageNum, pageSize);
        IPage<Orders> iPage = orderMapper.selectPage(page, null);
        return iPage;

    }
}
