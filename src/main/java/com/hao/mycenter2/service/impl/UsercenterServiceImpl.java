package com.hao.mycenter2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hao.mycenter2.service.UsercenterService;
import com.hao.mycenter2.model.Usercenter;
import com.hao.mycenter2.mapper.UsercenterMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
 *
* @author hao'qi'wei
* @description 针对表【usercenter(用户中心信息表)】的数据库操作Service实现
* @createDate 2024-03-01 16:55:10
*/
@Service
public class UsercenterServiceImpl extends ServiceImpl<UsercenterMapper, Usercenter>
    implements UsercenterService {

    @Resource
    private UsercenterMapper usercenterMapper;

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
        final String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String encryptPassword = DigestUtils.md5DigestAsHex((salt + userPassword).getBytes());
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
}




