package pers.xmy.simple.utils;

import org.springframework.http.HttpStatus;

/**
 * @author Yao
 * @version 1.0
 */

public class R {
    public static Result success() {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage("成功");
        result.setData(null);
        return result;
    }

    public static Result success(String message) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result success(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage("成功");
        result.setData(object);
        return result;
    }

    public static Result success(String message, Object object) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(message);
        result.setData(object);
        return result;
    }

    public static Result fail() {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage("失败");
        result.setData(null);
        return result;
    }

    public static Result fail(String message) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public static Result fail(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage("失败");
        result.setData(object);
        return result;
    }

    public static Result fail(String message, Object object) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        result.setMessage(message);
        result.setData(object);
        return result;
    }

    public static Result fail(Integer code, String message, Object object) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(object);
        return result;
    }

    public static Result fail(Integer code, String message){
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(message);
        return result;
    }

    // 用户模块200
    public static Result LOGIN_SUCCESS(Object object)             { return success("用户登录成功", object); }
    public static Result LOGOUT_SUCCESS()                         { return success("用户登出成功"); }
    public static Result REGISTER_SUCCESS()                       { return success("用户注册成功"); }
    public static Result GET_PROFILE_SUCCESS(Object object)       { return success("用户信息获取成功", object); }
    public static Result UPDATE_PROFILE_SUCCESS(Object object)    { return success("用户信息更新成功", object); }
    public static Result GET_USER_LIST_SUCCESS(Object object)     { return success("用户列表获取成功", object); }
    public static Result GET_PAGE_SUCCESS(Object object)          { return success("分页获取成功", object); }


    // 服务模块5001XX
    public static Result SERVER_ERROR()           { return fail(500100, "服务器异常"); }
    public static Result REQUEST_ILLEGAL()        { return fail(500102, "请求非法"); }
    public static Result ACCESS_LIMIT_REACHED()   { return fail(500104, "访问太频繁"); }

    // 用户模块 5002xx
    public static Result OBJECT_NOT_EXISTS()      { return fail(500210, "对象不存在"); }
    public static Result USERNAME_EMPTY()         { return fail(500212, "用户名不能为空"); }
    public static Result USERNAME_ERROR()         { return fail(500213, "用户名格式错误"); }
    public static Result USERNAME_NOT_EXISTS()    { return fail(500214, "用户名不存在"); }
    public static Result USERNAME_EXISTS()        { return fail(500215, "用户名已存在"); }
    public static Result MOBILE_EMPTY()           { return fail(500216, "手机号不能为空"); }
    public static Result MOBILE_ERROR()           { return fail(500217, "手机号格式错误"); }
    public static Result MOBILE_NOT_EXISTS()      { return fail(500218, "手机号不存在"); }
    public static Result PASSWORD_EMPTY()         { return fail(500219, "登录密码不能为空"); }
    public static Result PASSWORD_ERROR()         { return fail(500220, "密码错误"); }
    public static Result REGISTER_ERROR()         { return fail(500221, "用户注册失败"); }
    public static Result UPDATE_PROFILE_ERROR()   { return fail(500222, "用户信息更新失败"); }
}
