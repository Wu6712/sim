package pers.xmy.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.xmy.simple.entity.Orders;
import pers.xmy.simple.service.IOrderService;
import pers.xmy.simple.utils.R;
import pers.xmy.simple.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2023-08-05
 */
@Api(tags = "")
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Operation(summary = "列表")
    @PostMapping("/list")
    public Result list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        IPage<Orders> iPage = orderService.findList(pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("data", iPage.getRecords());
        res.put("total", iPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "列表2")
    @GetMapping("/list2")
    public Result list2(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        IPage<Orders> list = orderService.getList(pageNum, pageSize);

        if (list == null) {
            return R.fail("查询失败");
        }

        return R.success(list);

    }

    @Operation(summary = "更新")
    @PostMapping("/mod")
    public Result mod(@RequestBody Orders order) {
        return orderService.updateById(order) ? R.success("更新成功") : R.fail("更新失败");
    }

    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Result remove(Integer id) {
        return orderService.removeById(id) ? R.success("删除成功") : R.fail("删除失败");
    }

    @Operation(summary = "新增")
    @PostMapping("/add")
    public Result add(@RequestBody Orders order) {
        System.out.println(order);

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int digit = random.nextInt(10); // 生成0到9之间的随机数字
            stringBuilder.append(digit);
        }
        order.setNumber(stringBuilder.toString());
        order.setStatus(1);
        order.setPayMethod(0);
        return orderService.save(order) ? R.success("新增成功") : R.fail("新增失败");
    }

    @Operation(summary = "批量删除")
    @PostMapping("/remove/batch/{ids}")
    public Result removeBatch(@PathVariable(name = "ids") List<Integer> ids){
        return orderService.removeByIds(ids) ? R.success("批量删除成功") : R.fail("批量删除失败");
    }
}

