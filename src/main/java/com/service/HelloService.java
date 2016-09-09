package com.service;

import com.dao.UserInfoDao;
import com.model.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("helloService")
@Repository
public class HelloService implements IHelloService {
    private UserInfoDao userDao;

    public UserInfoDao getUserDao() {
        return userDao;
    }

    @Resource
    public void setUserDao(UserInfoDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int addUser(String userName) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name(userName);
        userDao.getSession().save(userInfo);
        return 1;
    }
}
