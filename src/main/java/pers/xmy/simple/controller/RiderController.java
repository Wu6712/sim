package pers.xmy.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.xmy.simple.entity.Rider;
import pers.xmy.simple.entity.SimpleUser;
import pers.xmy.simple.service.IRiderService;
import pers.xmy.simple.utils.Auth0Jwt;
import pers.xmy.simple.utils.R;
import pers.xmy.simple.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2023-08-11
 */
@Api(tags = "骑手模块")
@RestController
@RequestMapping("/rider")
@CrossOrigin
public class RiderController {
    @Autowired
    private IRiderService riderService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@ApiParam(value = "用户名", name = "username", required = true) @RequestParam("username") String username,
                           @ApiParam(value = "用户名", name = "password", required = true) @RequestParam("password") String password) {
        Rider rider = riderService.register(username, password);
        return rider != null ? R.success(rider) : R.fail("注册失败");
    }

    @Operation(summary = "用户登入")
    @PostMapping("/login")
    public Result login(@ApiParam(value = "用户名", name = "username", required = true) @RequestParam("username") String username,
                        @ApiParam(value = "密码", name = "password", required = true) @RequestParam("password") String password) {
        Rider rider = riderService.login(username, password);
        return rider != null ? R.LOGIN_SUCCESS(Auth0Jwt.sign(rider.getId(), rider.getUsername(), rider.getPassword())) : R.fail("登录失败");
    }

    @Operation(summary = "修改密码")
    @PostMapping("/info/password")
    public Result savePassword(@RequestParam Integer id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return riderService.savePassword(id, oldPassword, newPassword) != null ? R.success("修改成功") : R.fail("修改失败");
    }

    @Operation(summary = "修改密码2")
    @PostMapping("/info/password2")
    public Result savePassword2(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return riderService.savePassword2(username, oldPassword, newPassword) != null ? R.success("修改成功") : R.fail("修改失败");
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result logout(Integer id) {
        return riderService.logout(id) ? R.success("退出成功") : R.fail("退出失败");
    }

    @Operation(summary = "用户注销")
    @PostMapping("/logoff")
    public Result logoff(Integer id) {
        return riderService.removeById(id) ? R.success("注销成功") : R.fail("注销失败");
    }

    @Operation(summary = "用户更新")
    @PostMapping("/save")
    public Result save(@RequestBody Rider rider) {
        return riderService.updateById(rider) ? R.success("更新成功") : R.fail("更新失败");
    }

    @Operation(summary = "用户列表")
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        IPage<Rider> userPage = riderService.findList(pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("data", userPage.getRecords());
        res.put("total", userPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "名字模糊查询")
    @PostMapping("/fuzzyList")
    public Result fuzzyList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username) {
        IPage<Rider> userPage = riderService.findListByName(pageNum, pageSize, username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", userPage.getRecords());
        res.put("total", userPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "批量删除")
    @PostMapping("/remove/batch")
    public Result removeBatch(List<Integer> ids){
        return riderService.removeByIds(ids) ? R.success("批量删除成功") : R.fail("批量删除失败");
    }

    @Operation(summary = "骑手详情")
    @GetMapping
    public Result userDetail(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        Integer id = Auth0Jwt.getUserId(token).intValue();
        Rider riderInfo = riderService.getRiderInfo(id);

        if (riderInfo == null) {
            return R.fail("查询失败");
        }

        riderInfo.setPassword(null);
        return R.success(riderInfo);

    }

}

