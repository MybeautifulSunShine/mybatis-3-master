package org.apache.ibatis.learning.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * 描述:
 *
 * @author HGJ
 * @version 1.0
 * @create 2021-01-05 14:55
 */
@Slf4j
public class HelloWorldTest {
    /**
     * Myb
       * Mybatis全局配置文件
       */
    private static final String GLOBAL_CONFIG_FILE = "org/apache/ibatis/learning/helloworld/hello-world.xml";


    @Test
    public void helloWord() {
        // 获取Mybatis会话工厂的建造器
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 创建Mybatis的会话工厂
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(loadStream(GLOBAL_CONFIG_FILE));


        try (
                // 通过SqlSessionFactory获取一个SqlSession
                SqlSession sqlSession = sqlSessionFactory.openSession();
        ) {
            // 从mybatis中获取UserMapper的代理对象
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);


            final int id = 1;
            final String name = "jpanda";


            log.debug("在数据库新增了用户数据:id={},name={}", id, name);
            Integer updateCount = userMapper.insert(id, name);
            assert updateCount == 1;


            // 查询该用户
            User user = userMapper.getById(id);
            log.debug("获取数据用户id为{}的用户数据为:{}", id, user);
            // 回滚
            sqlSession.rollback();
        }
    }


    /**
     * 获取指定文件的输入流
     *
     * @param path 文件地址
     * @return 输入流
     */
    private InputStream loadStream(String path) {
        return HelloWorldTest.class.getClassLoader().getResourceAsStream(path);
    }
}
