package mybatis.spring;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.Resource;

import com.alibaba.druid.pool.DruidDataSource;

import mybatis.dao.UserMapper;
import mybatis.entity.User;
@Configuration
@ComponentScan("mybatis.service")
@MapperScan("mybatis.dao")
//@ImportResource({ "classpath:applicationContext.xml" })
public class MyBatisConfig implements ApplicationListener<ApplicationEvent>,BeanNameAware{
//	  @Bean
//	  public UserMapper userMapper() throws Exception {
//	    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
//	    return sqlSessionTemplate.getMapper(UserMapper.class);
//	  }
	
//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
	
//	@Autowired
//	private SqlSessionTemplate sqlSessionTemplate;
	
//	  @Bean
//	  public MapperFactoryBean<UserMapper> userMapper() throws Exception {
//	    MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
//	    factoryBean.setSqlSessionFactory(sqlSessionFactory);
//	    return factoryBean;
//	  }
	
	@Bean
	public MyBatisCursorItemReader<User> myBatisCursorItemReader() throws Exception {
		MyBatisCursorItemReader<User> cursorReader=new MyBatisCursorItemReader<User>();
		cursorReader.setSqlSessionFactory(sqlSessionFactory().getObject());
		cursorReader.setQueryId("");
		return cursorReader;
	}
	  
	  @Bean
	  public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource(getBlogDataSource());
//	    Resource[] mapperLocations=new Resource[1];
//	    mapperLocations[0].
//		factoryBean.setMapperLocations(mapperLocations);
		return factoryBean;
	  }
	  
//	  @Bean
//	  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//	    factoryBean.setDataSource(getBlogDataSource());
//	    SqlSessionTemplate st=new SqlSessionTemplate(factoryBean.getObject());
//		return st;
//	  }
	  
//	  private static DataSource getBlogDataSource() {
//			DruidDataSource ds=new DruidDataSource();
//			ds.setUrl("jdbc:mysql://127.0.0.1:3306/mybatistest?useUnicode=true&useCursorFetch=true&useSSL=true&autoReconnect=true&serverTimezone=UTC&failOverReadOnly=false&maxReconnects=30");
//			ds.setUsername("mybatistest");
//			ds.setPassword("mybatistest");
//			return ds;
//		}

	  private static DataSource getBlogDataSource() throws SQLException {

		// 配置真实数据源
		Map<String, DataSource> dataSourceMap = new HashMap<>();

		// 配置第 1 个数据源
		DruidDataSource dataSource1=new DruidDataSource();
		dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/mybatistest?useUnicode=true&useCursorFetch=true&useSSL=true&autoReconnect=true&serverTimezone=UTC&failOverReadOnly=false&maxReconnects=30");
		dataSource1.setUsername("mybatistest");
		dataSource1.setPassword("mybatistest");
		dataSourceMap.put("ds0", dataSource1);



		// 配置 t_order 表规则
		ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("user", "ds0.user${0..1}");


		// 配置分表策略
		orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("age", "tableShardingAlgorithm"));

		// 省略配置 t_order_item 表规则...
		// ...

		// 配置分片规则
		ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
		shardingRuleConfig.getTables().add(orderTableRuleConfig);


		// 配置分表算法
		Properties tableShardingAlgorithmrProps = new Properties();
		tableShardingAlgorithmrProps.setProperty("algorithm-expression", "user${age % 2}");
		shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));

		// 创建 ShardingSphereDataSource
		Properties p= new Properties();
		p.put("sql-show", true);
		DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig),p) ;
		return dataSource;
	  }
	  
	  
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println(event.getTimestamp()+event.toString());
		
	}

	@Override
	public void setBeanName(String name) {
		System.out.println(name);
		
	}
}
