package com.hao.mycenter2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.mycenter2.service.UsercenterService;
import com.hao.mycenter2.model.Usercenter;
import com.hao.mycenter2.mapper.UsercenterMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hao.mycenter2.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现类
 *
* @author hao'qi'wei
* @description 针对表【usercenter(用户中心信息表)】的数据库操作Service实现
* @createDate 2024-03-01 16:55:10
*/
@Service
@Slf4j
public class UsercenterServiceImpl extends ServiceImpl<UsercenterMapper, Usercenter>
    implements UsercenterService {

    @Resource
    private UsercenterMapper usercenterMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "awei";



    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1，校验
        if(StringUtils.isAllBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        if(userAccount.length() < 4){
            return -1;
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }
        // 特殊字符的校验
        String vaildPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\s]";
        Matcher matcher = Pattern.compile(vaildPattern).matcher(userAccount);
        if(matcher.find()){
            return -1;
        }
        //密码和校验码相同
        if(!userPassword.equals(checkPassword)){
            return -1;
        }
        //账户不能重复
        QueryWrapper<Usercenter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = usercenterMapper.selectCount(queryWrapper);
        if(count > 0){
            return -1;
        }
        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3，保存到数据库
        Usercenter usercenter = new Usercenter();
        usercenter.setUseraccount(userAccount);
        usercenter.setUserpassword(encryptPassword);
        boolean saveResult = this.save(usercenter);
        if(!saveResult){
            return -1;
        }
        return usercenter.getId();
    }

    @Override
    public Usercenter dologin(String userAccount, String userPassword, HttpServletRequest request) {
        //1，校验
        if(StringUtils.isAllBlank(userAccount,userPassword)){
            //todo 修改为自定义异常
            return null;
        }
        if(userAccount.length() < 4){
            return null;
        }
        if(userPassword.length() < 8){
            return null;
        }
        // 特殊字符的校验
        String vaildPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\s]";
        Matcher matcher = Pattern.compile(vaildPattern).matcher(userAccount);
        if(matcher.find()){
            return null;
        }

        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //登录逻辑
        QueryWrapper<Usercenter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        Usercenter user = usercenterMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("user login failed");
            return null;
        }
        Usercenter safetyUser = getSafety(user);
        //4，记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,safetyUser);
        return user;
    }

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    @Override
    public Usercenter getSafety(Usercenter originUser){
        //3，用户脱敏
        Usercenter safetyUser = new Usercenter();
        safetyUser.setId(originUser.getId());
        safetyUser.setUserpassword(null);
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setAvatarurl(originUser.getAvatarurl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setRole(originUser.getRole());
        safetyUser.setUseraccount(originUser.getUseraccount());
        safetyUser.setUserstatus(originUser.getUserstatus());
        return safetyUser;
    }
}




