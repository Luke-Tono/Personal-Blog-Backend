package com.luke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luke.domain.entity.RoleMenu;
import com.luke.domain.entity.RoleMenu;
import com.luke.mapper.RoleMenuMapper;
import com.luke.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public void deleteRoleMenuByRoleId(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId,id);
        remove(queryWrapper);
    }
}
