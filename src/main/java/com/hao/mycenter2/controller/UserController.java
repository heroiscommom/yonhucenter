package com.hao.mycenter2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hao.mycenter2.constant.UserConstant;
import com.hao.mycenter2.model.Usercenter;
import com.hao.mycenter2.model.request.UserLoginRequest;
import com.hao.mycenter2.model.request.UserRegisterRequest;
import com.hao.mycenter2.service.UsercenterService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hao.mycenter2.constant.UserConstant.ADMIN_ROLE;
import static com.hao.mycenter2.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 * @author awei
 */
@RestController
@RequestMapping("/user")
//适用于编写rest风格的代码
public class UserController {
    @Resource
    private UsercenterService usercenterService;
    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAllBlank(userAccount,userPassword,checkPassword)){
            return null;
        }
        return usercenterService.userRegister(userAccount, userPassword, checkPassword);
    }
    @PostMapping("/login")
    public Usercenter userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAllBlank(userAccount,userPassword)){
            return null;
        }
        return usercenterService.dologin(userAccount, userPassword,request);
    }

    @GetMapping("/search")
    public List<Usercenter> searchUser(String username,HttpServletRequest request){
        if(!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<Usercenter> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<Usercenter> userList = usercenterService.list(queryWrapper);
        return userList.stream().map(Usercenter -> usercenterService.getSafety(Usercenter)).collect(Collectors.toList());
    }
    @GetMapping("/delete")
    public boolean deleteUser(@RequestBody long id,HttpServletRequest request){
       if(!isAdmin(request)){
           return false;
       }
        if(id <= 0){
            return false;
        }
        return usercenterService.removeById(id);
    }

    /**
     * 是否为管理员
     * @param request
     * @return 是否为管理员
     */
    private boolean isAdmin(HttpServletRequest request){
        //鉴权，仅管理员可查询
        Object userobj = request.getSession().getAttribute(USER_LOGIN_STATE);
        Usercenter usercenter = (Usercenter) userobj;
        if(usercenter == null || usercenter.getRole() != ADMIN_ROLE){
            return false;
        }
        return true;
    }

}
