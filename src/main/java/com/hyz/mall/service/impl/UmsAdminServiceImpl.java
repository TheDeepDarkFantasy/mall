package com.hyz.mall.service.impl;

import com.hyz.mall.common.utils.JwtTokenUtil;
import com.hyz.mall.dao.UmsAdminRoleRelationDao;
import com.hyz.mall.mbg.mapper.UmsAdminMapper;
import com.hyz.mall.mbg.model.UmsAdmin;
import com.hyz.mall.mbg.model.UmsAdminExample;
import com.hyz.mall.mbg.model.UmsPermission;
import com.hyz.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/6 14:54
 */
@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UmsAdminServiceImpl.class);


    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private  UmsAdminMapper adminMapper;

    @Autowired
    private   UmsAdminRoleRelationDao adminRoleRelationDao;


    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
        if (umsAdmins!=null && umsAdmins.size()>0){
            return umsAdmins.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        UmsAdmin admin= new UmsAdmin();
        BeanUtils.copyProperties(umsAdmin, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        //查询是否有相同的用户
        UmsAdminExample example= new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
        if (umsAdmins.size()>0){
            return null;
        }

        //将密码加密
        String encode = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);
        adminMapper.insert(admin);
        return admin;
    }

    @Override
    public String login(String username, String password) {
        String token=null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token=jwtTokenUtil.generateToken(userDetails);

        } catch (AuthenticationException e){
            LOGGER.warn("登录异常：{}",e.getMessage());
        }

        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);

    }
}
