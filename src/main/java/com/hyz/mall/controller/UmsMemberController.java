package com.hyz.mall.controller;

import com.hyz.mall.common.api.CommonResult;
import com.hyz.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author hyz
 * @date 2020/05/06
 */

@Controller
@RequestMapping("/sso")
@Api(tags = "UmsMemberController",description = "会员注册")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UmsMemberController {
    @Autowired
    private  UmsMemberService umsMemberService;


    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone){
        return umsMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码")
    @PostMapping(value = "/verifyAuthCode")
    @ResponseBody
    public CommonResult verifyAuthCode(@RequestParam String telephone,@RequestParam String authCode){
        return umsMemberService.verifyAuthCode(telephone, authCode);

    }




}
