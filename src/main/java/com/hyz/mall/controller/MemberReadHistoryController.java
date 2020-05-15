package com.hyz.mall.controller;

import com.hyz.mall.common.api.CommonResult;
import com.hyz.mall.nosql.mongodb.document.MemberReadHistory;
import com.hyz.mall.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 * @author hyz
 * @version 1.0
 * @date 2020/5/15 15:40
 */
@Controller
@RequestMapping("/member/readHistory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "MemberReadHistoryController",description = "会员商品浏览记录管理")
public class MemberReadHistoryController {

    private final MemberReadHistoryService memberReadHistoryService;


    @ResponseBody
    @ApiOperation(value = "创建浏览记录")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory){
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }

    }

    @ApiOperation("删除浏览记录")
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids")List<String> ids){
        int count = memberReadHistoryService.delete(ids);
        if (count>0){
            return CommonResult.success(count);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询浏览记录")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult list(Long memberId){
        List<MemberReadHistory> list = memberReadHistoryService.list(memberId);
        return CommonResult.success(list);
    }

}
