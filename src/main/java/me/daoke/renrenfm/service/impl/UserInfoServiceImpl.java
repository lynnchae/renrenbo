package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.dao.IUserInfoDao;
import me.daoke.renrenfm.service.IUserInfoService;
import me.daoke.renrenfm.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录用户Service
 * Created by zhaoym on 2015/8/17.
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    /**
     *
     * @param userName 登录用户的姓名
     * @param userPassword 登录用户的密码
     * @return
     */
    @Override
    public boolean queryUserInfo(HttpSession session,String userName,String userPassword) {
        boolean flag = false;//默认未找到用户
        Map map = new HashMap<String,Object>();
        map.put("userName",userName);
        map.put("userPassword",userPassword);
        UserInfo userInfo = userInfoDao.selectOne("queryUserInfo",map);
        if(userInfo != null){
            session.setAttribute("user",userInfo);
            flag = true;
        }
        return flag;
    }

}
