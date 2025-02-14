package com.luke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author makejava
 * @since 2025-02-09 22:33:12
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);

    List<Long> selectRoleIdByUserId(Long userId);
}

