package pers.xmy.simple.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import pers.xmy.simple.entity.Dish;
import pers.xmy.simple.service.IDishService;
import pers.xmy.simple.utils.R;
import pers.xmy.simple.utils.Result;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yao
 * @since 2023-06-20
 */
@Api(tags = "")
@RestController
@RequestMapping("/dish")
@CrossOrigin
public class DishController {
    @Autowired
    private IDishService dishService;

    @Operation(summary = "列表")
    @GetMapping("/list")
    public Result list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        System.out.println(pageNum);
        IPage<Dish> iPage = dishService.findList(pageNum, pageSize);
        Map<String, Object> res = new HashMap<>();
        res.put("data", iPage.getRecords());
        res.put("total", iPage.getTotal());
        return R.success("查询成功", res);
    }

    @Operation(summary = "列表2")
    @GetMapping("/list2")
    public Result list2(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        IPage<Dish> list2 = dishService.findList2(pageNum, pageSize);

        return list2 == null ? R.fail("查询失败") : R.success(list2);

    }

    @Operation(summary = "更新")
    @PostMapping("/mod")
    public Result mod(@RequestBody Dish dish) {
        dish.setSum(0);
        dish.setIsCollect(0);
        System.out.println(dish);
        return dishService.updateById(dish) ? R.success("更新成功") : R.fail("更新失败");
    }

    @Operation(summary = "删除")
    @PostMapping("/remove")
    public Result remove(Integer id) {
        return dishService.removeById(id) ? R.success("删除成功") : R.fail("删除失败");
    }

    @Operation(summary = "新增")
    @PostMapping("/add")
    public Result add(MultipartFile file,
                      Dish dish) throws IOException {
        System.out.println(file);
        System.out.println(dish);

        String uploadDir = "D:\\image\\";
        // 创建文件夹（如果不存在）
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // 创建文件
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + fileName;
        File targetFile = new File(filePath);
        file.transferTo(targetFile);

        filePath = "http://127.0.0.1:8888/image/" + fileName;
        dish.setImage(filePath);
        dish.setSum(0);
        dish.setIsCollect(0);

        System.out.println(dish);

        boolean i = dishService.save(dish);

        System.out.println(i);

        if (!i) {
            return R.fail("新增失败");
        }

        return R.success("新增成功");
    }

    @Operation(summary = "批量删除")
    @PostMapping("/remove/batch/{ids}")
    public Result removeBatch(@PathVariable(name = "ids") List<Integer> ids) {

        return dishService.removeByIds(ids) ? R.success("批量删除成功") : R.fail("批量删除失败");
    }

//    @Operation(summary = "根据菜单Id查找")
//    @GetMapping("/IdList")
//    public Result idList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam Integer id) {
//        IPage<Dish> dishPage = dishService.findListByMenuId(pageNum, pageSize, id);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", dishPage.getRecords());
//        res.put("total", dishPage.getTotal());
//        return R.success("查询成功", res);
//    }

    @Operation(summary = "根据菜单Id查找")
    @GetMapping("/IdList")
    public Result idList(@RequestParam Integer id) {
        List<Dish> page = dishService.findPageById(id);
        return page != null ? R.success("查询成功", page) : R.fail("查询失败");
    }

    @Operation(summary = "最热销五种商品")
    @GetMapping("/hot")
    public Result hotList() {
        List<Dish> page = dishService.findPage5();
        return page != null ? R.success("查询成功", page) : R.fail("查询失败");
    }

    @Operation(summary = "通过Id查出一个")
    @GetMapping("/getOne")
    public Result oneList(@RequestParam Integer id) {
        List<Dish> page = dishService.findListById(id);
        return page != null ? R.success("查询成功", page) : R.fail("查询失败");
    }

    @Operation(summary = "收藏")
    @PostMapping("/collect")
    public Result collect(@RequestParam Integer id) {
        Integer collect = dishService.collect(id);
        return collect == 1 ? R.success("收藏成功") : R.fail("收藏失败");
    }

    @Operation(summary = "取消收藏")
    @PostMapping("/cancelCollect")
    public Result cancelCollect(@RequestParam Integer id) {
        Integer collect = dishService.cancelCollect(id);
        return collect == 1 ? R.success("取消成功") : R.fail("取消失败");
    }

    @Operation(summary = "查询所有收藏")
    @GetMapping("/allCollect")
    public Result allCollect() {
        List<Dish> collect = dishService.selectCollect();
        return collect != null ? R.success("查询成功", collect) : R.fail("查询失败");
    }
}

