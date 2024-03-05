package com.hao.mycenter2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hao.mycenter2.common.BaseResponse;
import com.hao.mycenter2.common.ErrorCode;
import com.hao.mycenter2.common.ResultUtils;
import com.hao.mycenter2.constant.UserConstant;
import com.hao.mycenter2.exception.BusinessException;
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
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            //return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessException(ErrorCode.NULL_ERROR,"注册参数缺失");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if(StringUtils.isAllBlank(userAccount,userPassword,checkPassword)){
            throw new BusinessException(ErrorCode.NULL_ERROR,"注册参数缺失");
        }
        long result = usercenterService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }
    @GetMapping("/current")
    public BaseResponse<Usercenter> currentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        Usercenter currentUser = (Usercenter) userObj;
        if(currentUser == null){
            return null;
        }
        Long userId = currentUser.getId();

        Usercenter user = usercenterService.getById(userId);
        Usercenter safetyUser = usercenterService.getSafety(user);
        return ResultUtils.success(safetyUser);
    }
    @PostMapping("/login")
    public BaseResponse<Usercenter> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAllBlank(userAccount,userPassword)){
            return null;
        }
        Usercenter dologin = usercenterService.dologin(userAccount, userPassword, request);
        return ResultUtils.success(dologin);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if(request == null){
            return null;
        }
        int result = usercenterService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/search")
    public BaseResponse<List<Usercenter>> searchUser(String username,HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        QueryWrapper<Usercenter> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<Usercenter> userList = usercenterService.list(queryWrapper);
        List<Usercenter> result = userList.stream().map(Usercenter -> usercenterService.getSafety(Usercenter)).collect(Collectors.toList());
        return ResultUtils.success(result);
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
