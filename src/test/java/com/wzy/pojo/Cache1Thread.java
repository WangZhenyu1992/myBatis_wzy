package com.wzy.pojo;

import com.wzy.mybatis.domain.UserInfo;
import com.wzy.mybatis.mapper.UserInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Cache1Thread implements Runnable {


    public void run() {
        SqlSession sqlSession = null;
        try{
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sessionFactory.openSession();
        }catch (IOException e){
            e.printStackTrace();
        }

        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
        System.out.println(userInfo);
        try{
            Thread.sleep(1000);
            new Cache2Thread().run();
        }catch (Exception e){
            e.printStackTrace();
        }

        UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(1);
        System.out.println(userInfo1);
    }
}
