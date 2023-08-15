package pers.xmy.simple.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.xmy.simple.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2023-08-05
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}