package pers.xmy.simple.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pers.xmy.simple.entity.Rider;
import pers.xmy.simple.entity.SimpleUser;
import pers.xmy.simple.mapper.RiderMapper;
import pers.xmy.simple.service.IRiderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2023-08-11
 */
@Service
public class RiderServiceImpl extends ServiceImpl<RiderMapper, Rider> implements IRiderService {

    @Autowired
    private RiderMapper riderMapper;

    @Override
    public Rider register(String username, String password) {
        Rider rider = new Rider();
        rider.setUsername(username);
        rider.setPassword(new BCryptPasswordEncoder().encode(password.trim()));
        rider.setOnline(0);
        this.save(rider);
        return rider;
    }

    @Override
    public Rider login(String username, String password) {
        Rider rider = this.getOne(new QueryWrapper<Rider>().lambda().eq(Rider::getUsername, username.toLowerCase().trim()));
        UpdateWrapper<Rider> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username);
        updateWrapper.set("online", 1);
        int update = riderMapper.update(null, updateWrapper);
        if (new BCryptPasswordEncoder().matches(password, rider.getPassword())) {
            if (update == 1) {
                return rider;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Rider savePassword(Integer id, String oldPassword, String newPassword) {
        Rider rider = this.getOne(new QueryWrapper<Rider>().lambda().eq(Rider::getId, id));
        if (rider != null && new BCryptPasswordEncoder().matches(oldPassword, rider.getPassword())) {
            rider.setPassword(new BCryptPasswordEncoder().encode(newPassword.trim()));
            riderMapper.updateById(rider);
            return rider;
        } else {
            return null;
        }
    }

    @Override
    public Boolean logout(Integer id) {
        UpdateWrapper<Rider> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("online", 0);
        int update = riderMapper.update(null, updateWrapper);
        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IPage<Rider> findList(Integer pageNum, Integer pageSize) {
        return this.page(new Page<>(pageNum,pageSize));
    }

    @Override
    public IPage<Rider> findListByName(Integer pageNum, Integer pageSize, String username) {
        return this.page(new Page<>(pageNum, pageSize), new QueryWrapper<Rider>().like("username", username));
    }

    @Override
    public Rider savePassword2(String username, String oldPassword, String newPassword) {
        Rider rider = this.getOne(new QueryWrapper<Rider>().lambda().eq(Rider::getUsername, username));
        if (rider != null && new BCryptPasswordEncoder().matches(oldPassword, rider.getPassword())) {
            rider.setPassword(new BCryptPasswordEncoder().encode(newPassword.trim()));
            riderMapper.updateById(rider);
            return rider;
        } else {
            return null;
        }
    }

    @Override
    public Rider getRiderInfo(Integer id) {

        return riderMapper.getInfoById(id);

    }
}
