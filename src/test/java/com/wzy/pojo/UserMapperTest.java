package com.wzy.pojo;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wzy.mybatis.domain.RunoobTbl;
import com.wzy.mybatis.domain.UserInfo;
import com.wzy.mybatis.domain.UserInfoExample;
import com.wzy.mybatis.mapper.UserInfoMapper;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class UserMapperTest {
    /**
     * 多种使用mybatis的方法
     */
    @Test
    public void select(){
        SqlSession openSession = getSqlSession();

        UserInfoExample example = new UserInfoExample();
        //UserInfoExample.Criteria criteria = example.createCriteria();
        //criteria.andUsernameLike("wzy");
        //criteria.andUsernameBetween("w", "y");

        //直接读取配置方式
        //List<UserInfo> userDtoList = openSession.selectList("selectByExample", example);
        RunoobTbl userInfo = openSession.selectOne("com.wzy.mybatis.mapper.RunoobTblMapper.selectByPrimaryKey", 1);
        System.out.println(userInfo);
        //接口方式
        //UserInfoMapper userInfoMapper = openSession.getMapper(UserInfoMapper.class);
//        List<UserInfo> userDtoList = userInfoMapper.selectByExampleWithBLOBs(example);

        //注解方式
        //UserInfo userInfo = userInfoMapper.selectByPrimaryKey(4);

        //验证缓存
        //这样不能验证是因为有一个清空策略，当update等的时候 或清空缓存 重新读取
//        System.out.println(userDtoList.get(3));
//        userDtoList.get(3).setPassword("abcdefg");
////        openSession.update("updateByPrimaryKey", userDtoList.get(3));
////        openSession.commit();
//        List<UserInfo> userInfoCaches = openSession.selectList("selectByExample", example);
//        System.out.println(userInfoCaches.get(3));


        //pagehelper分页插件使用
//        long total = PageHelper.count(() -> userInfoMapper.selectByExampleWithBLOBs(example));
//        System.out.println(total);
//
//        Page<UserInfo> page = PageHelper.startPage(2, 2).doSelectPage(()-> userInfoMapper.selectByExampleWithBLOBs(example));
//        System.out.println(page);


//        for(UserInfo userDto: userDtoList){
//            System.out.println(userDto.getAddress());
//        }

        //System.out.println(userInfo.getAddress());
    }

    @Test
    public void insert(){
        SqlSession openSession = getSqlSession();
        UserInfoMapper userInfoMapper = openSession.getMapper(UserInfoMapper.class);

        UserInfo userInfo = new UserInfo();
        userInfo.setAddress("贵州");
        userInfo.setAge(23);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, 10, 2);
        userInfo.setBirthday(calendar.getTime());
        userInfo.setUsername("xiaoqun");
        userInfo.setPassword("aA123456");

        userInfoMapper.insert(userInfo);
        openSession.commit();

        UserInfoExample userInfoExample = new UserInfoExample();
        System.out.println(userInfoMapper.selectByExampleWithBLOBs(userInfoExample));
    }

    /**
     * 验证以及缓存
     * @return
     */
    @Test
    public void isCache() throws Exception{
        new Cache1Thread().run();
    }

    /**
     * 多种使用mybatis的方法
     */
    @Test
    public void selectSource(){
        UserInfoMapper userInfoMapper = getSqlSession().getMapper(UserInfoMapper.class);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
        System.out.println(userInfo);

    }

    private SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        try{
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sessionFactory.openSession();
        }catch (IOException e){
            e.printStackTrace();
        }

        return sqlSession;
    }
}
