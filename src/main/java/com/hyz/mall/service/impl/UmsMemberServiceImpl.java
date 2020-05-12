package com.hyz.mall.service.impl;

import com.hyz.mall.common.api.CommonResult;
import com.hyz.mall.service.RedisService;
import com.hyz.mall.service.UmsMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author hyz
 * @date 2020/05/06
 */

@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private  RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PRIFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;




    /**
     * 生成验证码
     *
     * @param telephone
     * @return
     */
    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        for (int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        //Long expireTime=Long.valueOf(AUTH_CODE_EXPIRE_SECONDS);
        //绑定 手机号，存储到redis中
        redisService.set(REDIS_KEY_PRIFIX_AUTH_CODE+telephone, sb.toString());
        redisService.exprire(REDIS_KEY_PRIFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");


    }

    /**
     * 判断验证码和手机号码是否匹配
     *
     * @param telephone
     * @param auchCode
     * @return
     */
    @Override
    public CommonResult verifyAuthCode(String telephone, String auchCode) {
        if (StringUtils.isEmpty(telephone)){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PRIFIX_AUTH_CODE + telephone);
        boolean result = auchCode.equals(realAuthCode);
        if (result){
            return CommonResult.success(null,"验证码校验成功");
        }else {
            return CommonResult.failed("验证码校验不成功");
        }



    }
}
