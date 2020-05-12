package com.hyz.mall.controller;
import	java.util.HashMap;
import java.util.List;
import	java.util.Map;

import com.hyz.mall.common.api.CommonResult;
import com.hyz.mall.dto.UmsAdminLoginParam;
import com.hyz.mall.mbg.model.UmsAdmin;
import com.hyz.mall.mbg.model.UmsPermission;
import com.hyz.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/6 17:54
 */

@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
	@Autowired
	private UmsAdminService adminService;

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;

    @ApiOperation(value = "用户注册")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        	        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        	        if (umsAdmin == null) {
            	            CommonResult.failed();
            	        }
        	        return CommonResult.success(umsAdmin);
    }

	@ApiOperation(value = "登录后返回token")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminParam, BindingResult result){
		String token = adminService.login(umsAdminParam.getUsername(), umsAdminParam.getPassword());
		if (token==null){
			return CommonResult.validateFailed("用户名密码错误");
		}
		Map<String, Object> tokenMap = new HashMap<String, Object> ();
		tokenMap.put("token",token);
		tokenMap.put("tokenHead",tokenHead);
		return CommonResult.success(tokenMap);



	}

	@ApiOperation(value = "获取用户全部权限")
	@RequestMapping(value = "/permission{adminId}",method = RequestMethod.GET)
	@ResponseBody
	public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
		List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
		return CommonResult.success(permissionList);
	}


}
