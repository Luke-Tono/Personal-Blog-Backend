package com.luke.service.impl;


import com.luke.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ps")
public class PermissionsService {

    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission){
        // 如果是超级管理员 直接返回true
        if(SecurityUtils.isAdmin()){
            return true;
        }

        //否则 获取当前登录用户所具有的权限列表
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);

    }
}
