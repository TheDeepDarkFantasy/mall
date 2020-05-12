package com.hyz.mall.controller;

import com.hyz.mall.common.api.CommonPage;
import com.hyz.mall.common.api.CommonResult;
import com.hyz.mall.mbg.model.PmsBrand;
import com.hyz.mall.service.PmsBrandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌管理controller
 */

@Controller
@RequestMapping("/brand")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PmsBrandController {

    @Autowired
    private  PmsBrandService brandService;

    public static final Logger LOGGER= LoggerFactory.getLogger(PmsBrandController.class);

    @RequestMapping(value = "listAll",method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('pms:brand:read')")
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(brandService.listAllBrand());
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand brand){
        CommonResult commonResult;
        int count = brandService.createBrand(brand);
        if (count==1){
            commonResult=CommonResult.success(brand);
            LOGGER.debug("create success:"+brand);
        }else{
            commonResult=CommonResult.failed("操作失败");
            LOGGER.debug("create failed:"+brand);
        }

        return commonResult;

    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id,@RequestBody PmsBrand brand){
        CommonResult commonResult;
        int count = brandService.updateBrand(id, brand);
        if (count==1){
            commonResult=CommonResult.success(brand);
            LOGGER.debug("update Brand success:"+brand);
        }else{
            commonResult=CommonResult.failed();
            LOGGER.debug("update brand failed:"+brand);
        }
        return commonResult;
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody

    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }






}
