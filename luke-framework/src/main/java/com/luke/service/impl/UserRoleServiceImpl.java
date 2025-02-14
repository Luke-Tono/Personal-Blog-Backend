package com.luke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luke.domain.entity.UserRole;
import com.luke.mapper.UserRoleMapper;
import com.luke.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
