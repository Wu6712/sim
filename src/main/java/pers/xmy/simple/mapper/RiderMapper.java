package pers.xmy.simple.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.xmy.simple.entity.Rider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2023-08-11
 */
@Mapper
public interface RiderMapper extends BaseMapper<Rider> {

    Rider getInfoById(Integer id);

}
