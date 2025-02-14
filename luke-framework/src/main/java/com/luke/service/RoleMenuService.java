package com.luke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luke.domain.entity.RoleMenu;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}