package pers.xmy.simple.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import pers.xmy.simple.entity.Rider;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.xmy.simple.entity.SimpleUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2023-08-11
 */
public interface IRiderService extends IService<Rider> {

    Rider register(String username, String password);

    Rider login(String username, String password);

    Rider savePassword(Integer id, String oldPassword, String newPassword);

    Boolean logout(Integer id);

    IPage<Rider> findList(Integer pageNum, Integer pageSize);

    IPage<Rider> findListByName(Integer pageNum, Integer pageSize, String username);

    Rider savePassword2(String username, String oldPassword, String newPassword);

    Rider getRiderInfo(Integer id);

}
