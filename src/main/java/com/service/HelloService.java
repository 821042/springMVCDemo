package com.service;

import com.dao.UserInfoDao;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;

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
    public int addUser(Object o) throws Exception {
        userDao.getSession().save(o);
        ClassMetadata classMetadata =  userDao.getSessionFactory().getClassMetadata(o.getClass());
        String identifierName = classMetadata.getIdentifierPropertyName();
        Field[] farr = o.getClass().getDeclaredFields();

        for(Field field:farr){
            field.setAccessible(true);
            if(field.getName().equals(identifierName)){
                System.out.println(field.get(o));
            }

        }
        System.out.println("*****="+identifierName);
        return 1;
    }
}
