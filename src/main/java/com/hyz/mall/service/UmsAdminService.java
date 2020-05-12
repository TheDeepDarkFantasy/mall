package com.hyz.mall.service;

import com.hyz.mall.mbg.model.UmsAdmin;
import com.hyz.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/6 14:51
 */
public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    UmsAdmin register(UmsAdmin umsAdmin);

    String login(String username,String password);

    List<UmsPermission> getPermissionList(Long adminId);
}
