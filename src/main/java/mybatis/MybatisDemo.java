package mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.alibaba.druid.pool.DruidDataSource;

import mybatis.dao.UserMapper;
import mybatis.entity.User;

public class MybatisDemo {
public static void main(String[] args) throws InterruptedException {
	DataSource dataSource = getBlogDataSource();
	TransactionFactory transactionFactory = new JdbcTransactionFactory();
	Environment environment = new Environment("development", transactionFactory, dataSource);
	Configuration configuration = new Configuration(environment);
	configuration.addMapper(UserMapper.class);
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	SqlSession ss=sqlSessionFactory.openSession();
	User user=ss.getMapper(UserMapper.class).selectUser(1);
	System.out.println(Thread.currentThread().toString()+user);
	UserMapper m1=ss.getMapper(UserMapper.class);
    System.out.println(Thread.currentThread().toString()+m1);

	UserMapper m2=ss.getMapper(UserMapper.class);
    System.out.println(Thread.currentThread().toString()+m2);

	ss.commit();
//    Thread.sleep(1000L);
//	new Thread(()->{
//		SqlSession ss1=sqlSessionFactory.openSession();
//		User user1=ss1.getMapper(UserMapper.class).selectUser(1);
//		System.out.println(Thread.currentThread().toString()+user1);
//	}).start();
	
}

private static DataSource getBlogDataSource() {
	DruidDataSource ds=new DruidDataSource();
	ds.setUrl("jdbc:mysql://127.0.0.1:3306/mybatistest?useUnicode=true&useSSL=true&autoReconnect=true&serverTimezone=UTC&failOverReadOnly=false&maxReconnects=30");
	ds.setUsername("mybatistest");
	ds.setPassword("mybatistest");
	return ds;
}
}
