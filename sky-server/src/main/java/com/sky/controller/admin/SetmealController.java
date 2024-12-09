package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @PostMapping
    @ApiOperation(value = "新增套餐接口")
    public Result addSetmeal(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐：{}",setmealDTO);
        setmealService.add(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询套餐数据")
    public Result getSetmealById(@PathVariable Long id) {
        log.info("查询id：{}的套餐",id);
        setmealService.selectById(id);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询套餐接口")
    public Result<PageResult> pageSetmeal(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询数据：{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.page(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("起售、停售接口")
    public Result startOrStopSetmeal(@PathVariable("status") Integer status, Long id) {
        log.info("id:{}状态改为{}",id,status);
        setmealService.startOrStop(status,id);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除套餐")
    public Result deleteSetmeal(@RequestParam List<Long> ids) {
        log.info("删除套餐的ids:{}",ids);
        setmealService.deleteSetmeals(ids);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改套餐")
    public Result updateSetmeal(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐信息为：{}",setmealDTO);
        setmealService.updateSetmeal(setmealDTO);
        return Result.success();
    }
}