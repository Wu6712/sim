package pers.xmy.simple.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.xmy.simple.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2023-04-13
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
