package com.hyz.mall.service;

import com.hyz.mall.common.api.CommonResult;

/**
 * @author hyz
 * @date 2020/05/06
 */

public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     * @param telephone
     * @param auchCode
     * @return
     */
    CommonResult verifyAuthCode(String telephone,String auchCode);

}
