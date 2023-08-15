package pers.xmy.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pers.xmy.simple.entity.SimpleUser;
import pers.xmy.simple.mapper.SimpleUserMapper;
import pers.xmy.simple.service.ISimpleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yao
 * @since 2023-04-05
 */
@Service
public class SimpleUserServiceImpl extends ServiceImpl<SimpleUserMapper, SimpleUser> implements ISimpleUserService {

    @Autowired
    private SimpleUserMapper simpleUserMapper;

    @Override
    public SimpleUser register(String username, String password) {
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setUsername(username);
        simpleUser.setPassword(new BCryptPasswordEncoder().encode(password.trim()));
        this.save(simpleUser);
        return simpleUser;
    }

    @Override
    public SimpleUser login(String username, String password) {
        SimpleUser simpleUser = this.getOne(new QueryWrapper<SimpleUser>().lambda().eq(SimpleUser::getUsername, username.toLowerCase().trim()));
        UpdateWrapper<SimpleUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username);
        updateWrapper.set("online", 1);
        int update = simpleUserMapper.update(null, updateWrapper);
        if (new BCryptPasswordEncoder().matches(password, simpleUser.getPassword())) {
            if (update == 1) {
                return simpleUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public SimpleUser savePassword(Integer id, String oldPassword, String newPassword) {
        SimpleUser simpleUser = this.getOne(new QueryWrapper<SimpleUser>().lambda().eq(SimpleUser::getId, id));
        if (simpleUser != null && new BCryptPasswordEncoder().matches(oldPassword, simpleUser.getPassword())) {
            simpleUser.setPassword(new BCryptPasswordEncoder().encode(newPassword.trim()));
            simpleUserMapper.updateById(simpleUser);
            return simpleUser;
        } else {
            return null;
        }
    }

    @Override
    public Boolean logout(Integer id) {
        UpdateWrapper<SimpleUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("online", 0);
        int update = simpleUserMapper.update(null, updateWrapper);
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IPage<SimpleUser> findList(Integer pageNum, Integer pageSize) {
        return new Page<>(pageNum, pageSize);
    }

    @Override
    public IPage<SimpleUser> findList2(Integer pageNum, Integer pageSize) {

        Page<SimpleUser> page = new Page<>(pageNum, pageSize);
        Page<SimpleUser> ipage = simpleUserMapper.selectPage(page, null);
        return ipage;

    }

    @Override
    public IPage<SimpleUser> findListByName(Integer pageNum, Integer pageSize, String username) {
        return this.page(new Page<>(pageNum, pageSize), new QueryWrapper<SimpleUser>().like("username", username));
    }

    @Override
    public SimpleUser savePassword2(String username, String oldPassword, String newPassword) {
        SimpleUser simpleUser = this.getOne(new QueryWrapper<SimpleUser>().lambda().eq(SimpleUser::getUsername, username));
        if (simpleUser != null && new BCryptPasswordEncoder().matches(oldPassword, simpleUser.getPassword())) {
            simpleUser.setPassword(new BCryptPasswordEncoder().encode(newPassword.trim()));
            simpleUserMapper.updateById(simpleUser);
            return simpleUser;
        } else {
            return null;
        }
    }

    @Override
    public SimpleUser getUserInfo(Integer id) {

        return simpleUserMapper.getOneById(id);

    }
}
