package com.nettlibrary.easychat.service;

import com.nettlibrary.easychat.bean.UserInfo;
import com.nettlibrary.easychat.dao.UserDao;

/**
 * Created by Administrator on 2018/1/5.
 */
public class UserService extends BaseService {
    UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public UserInfo register(String acc, String pwd) {
        UserInfo info = userDao.getUser(acc, pwd);
        return info;
    }
    public UserInfo login(long uid) {
        UserInfo info = userDao.getUser(uid);
        return info;
    }

}
