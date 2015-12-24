package me.daoke.renrenfm.service;

import javax.servlet.http.HttpSession;

/**
 * 登录用户Service
 * Created by zhaoym on 2015/8/17.
 */
public interface IUserInfoService {

    /**
     * 获取登录用户的信息
     * @param userName 用户的姓名
     * @param userPassword 用户的密码
     * @return
     */
    public boolean queryUserInfo(HttpSession session,String userName,String userPassword);

}
