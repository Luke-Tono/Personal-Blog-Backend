package com.luke.controller;

import com.luke.domain.ResponseResult;
import com.luke.domain.entity.LoginUser;
import com.luke.domain.entity.Menu;
import com.luke.domain.entity.User;
import com.luke.domain.vo.AdminUserInfoVo;
import com.luke.domain.vo.RoutersVo;
import com.luke.domain.vo.UserInfoVo;
import com.luke.enums.AppHttpCodeEnum;
import com.luke.exception.SystemException;
import com.luke.service.LoginService;


import com.luke.service.MenuService;
import com.luke.service.RoleService;
import com.luke.utils.BeanCopyUtils;
import com.luke.utils.RedisCache;
import com.luke.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }


    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        //封装数据返回

        AdminUserInfoVo adminUserInfoVo= new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        //查询menu 结果是tree形式
        Long userId = SecurityUtils.getUserId();
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);

        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }


    @PostMapping("/user/logout")
    public ResponseResult logout(){
       return loginService.logout();

    }

}