package com.hyz.mall.dto;

import com.hyz.mall.mbg.model.UmsAdmin;
import com.hyz.mall.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hyz
 * @version 1.0
 * @date 2020/5/6 14:13
 */
public class AdminUserDetails implements UserDetails {

    private UmsAdmin umsAdmin;

    private List<UmsPermission> permissionList;

    public AdminUserDetails(UmsAdmin umsAdmin,List<UmsPermission> permissionList){
        this.umsAdmin=umsAdmin;
        this.permissionList=permissionList;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限

        return permissionList.stream()
                .filter(permission->permission.getValue()!=null)
                .map(permission->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {

        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {

        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return umsAdmin.getStatus().equals(1);
    }
}
