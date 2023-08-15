package pers.xmy.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pers.xmy.simple.entity.Menu;
import pers.xmy.simple.service.IMenuService;
import pers.xmy.simple.utils.R;
import pers.xmy.simple.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2023-06-20
 */
@Api(tags = "")
@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @Operation(summary = "列表")
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        IPage<Menu> iPage = menuService.findList(pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("data", iPage.getRecords());
        res.put("total", iPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "更新")
    @PostMapping("/mod")
    public Result mod(@RequestBody Menu menu) {
        return menuService.updateById(menu) ? R.success("更新成功") : R.fail("更新失败");
    }

    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Result remove(Integer id) {
        return menuService.removeById(id) ? R.success("删除成功") : R.fail("删除失败");
    }

    @Operation(summary = "新增")
    @PostMapping("/add")
    public Result add(@RequestBody Menu menu) {
        return menuService.save(menu) ? R.success("新增成功") : R.fail("新增失败");
    }

    @Operation(summary = "批量删除")
    @PostMapping("/remove/batch/{ids}")
    public Result removeBatch(@PathVariable(name = "ids") List<Integer> ids){
        return menuService.removeByIds(ids) ? R.success("批量删除成功") : R.fail("批量删除失败");
    }
}

