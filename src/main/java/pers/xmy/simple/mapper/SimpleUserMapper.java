package pers.xmy.simple.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.xmy.simple.entity.SimpleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2023-04-05
 */
@Mapper
public interface SimpleUserMapper extends BaseMapper<SimpleUser> {

    SimpleUser getOneById(Integer id);

}
