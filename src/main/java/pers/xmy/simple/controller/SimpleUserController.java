package pers.xmy.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.xmy.simple.entity.SimpleUser;
import pers.xmy.simple.service.ISimpleUserService;
import pers.xmy.simple.utils.Auth0Jwt;
import pers.xmy.simple.utils.R;
import pers.xmy.simple.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yao
 * @since 2023-04-05
 */

@Api(tags = "用户模块")
@RestController
@RequestMapping("/simple-user")
@CrossOrigin
public class SimpleUserController {

    @Autowired
    private ISimpleUserService iSimpleUserService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@ApiParam(value = "用户名", name = "username", required = true) @RequestParam("username") String username,
                           @ApiParam(value = "用户名", name = "password", required = true) @RequestParam("password") String password) {
        SimpleUser simpleUser = iSimpleUserService.register(username, password);
        return simpleUser != null ? R.success(simpleUser) : R.fail("注册失败");
    }

    @Operation(summary = "用户登入")
    @PostMapping("/login")
    public Result login(@ApiParam(value = "用户名", name = "username", required = true) @RequestParam("username") String username,
                        @ApiParam(value = "密码", name = "password", required = true) @RequestParam("password") String password) {
        SimpleUser simpleUser = iSimpleUserService.login(username, password);
        return simpleUser != null ? R.LOGIN_SUCCESS(Auth0Jwt.sign(simpleUser.getId(), simpleUser.getUsername(), simpleUser.getPassword())) : R.fail("登录失败");
    }

    @Operation(summary = "修改密码")
    @PostMapping("/info/password")
    public Result savePassword(@RequestParam Integer id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return iSimpleUserService.savePassword(id, oldPassword, newPassword) != null ? R.success("修改成功") : R.fail("修改失败");
    }

    @Operation(summary = "修改密码2")
    @PostMapping("/info/password2")
    public Result savePassword2(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return iSimpleUserService.savePassword2(username, oldPassword, newPassword) != null ? R.success("修改成功") : R.fail("修改失败");
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public Result logout(Integer id) {
        return iSimpleUserService.logout(id) ? R.success("退出成功") : R.fail("退出失败");
    }

    @Operation(summary = "用户注销")
    @PostMapping("/logoff")
    public Result logoff(Integer id) {
        return iSimpleUserService.removeById(id) ? R.success("注销成功") : R.fail("注销失败");
    }

    @Operation(summary = "用户更新")
    @PostMapping("/save")
    public Result save(@RequestBody SimpleUser simpleUser) {
        return iSimpleUserService.updateById(simpleUser) ? R.success("更新成功") : R.fail("更新失败");
    }

    @Operation(summary = "用户列表")
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        IPage<SimpleUser> userPage = iSimpleUserService.findList(pageNum, pageSize);
        System.out.println(userPage.getRecords());
        Map<String, Object> res = new HashMap<>();
        res.put("data", userPage.getRecords());
        res.put("total", userPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "用户列表2")
    @GetMapping("/list2")
    public Result list2(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize) {

        IPage<SimpleUser> list2 = iSimpleUserService.findList2(pageNum, pageSize);

        return list2 == null ? R.fail("查询失败") : R.success(list2);

    }

    @Operation(summary = "名字模糊查询")
    @PostMapping("/fuzzyList")
    public Result fuzzyList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username) {
        IPage<SimpleUser> userPage = iSimpleUserService.findListByName(pageNum, pageSize, username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", userPage.getRecords());
        res.put("total", userPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "批量删除")
    @PostMapping("/remove/batch/{ids}")
    public Result removeBatch(@PathVariable(name = "ids") List<Integer> ids){
        return iSimpleUserService.removeByIds(ids) ? R.success("批量删除成功") : R.fail("批量删除失败");
    }

    @Operation(summary = "用户详情")
    @GetMapping
    public Result userDetail(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        Integer id = Auth0Jwt.getUserId(token).intValue();
        SimpleUser userInfo = iSimpleUserService.getUserInfo(id);

        if (userInfo == null) {
            return R.fail("查询失败");
        }

        userInfo.setPassword(null);
        return R.success(userInfo);

    }


}
