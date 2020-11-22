package mybatis.spring;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import mybatis.service.IUserService;


public class SpringMain {
	

public static void main(String[] args) throws UnexpectedInputException, ParseException, Exception {
	

	
	AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);
	String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
    for (String name : beanDefinitionNames){
        System.out.println(name);
    }
    IUserService userService= applicationContext.getBean(IUserService.class);
    System.out.println(userService.getUserByAge(30));
//    userService.getUserByCursor();
//    UserMapper mapper1=(UserMapper) applicationContext.getBean("userMapper");
//    System.out.println(Thread.currentThread().toString()+mapper1);
//    UserMapper mapper2=(UserMapper) applicationContext.getBean("userMapper");
//    System.out.println(Thread.currentThread().toString()+mapper2);
//    User user= mapper1.selectUser(1);
//	System.out.println(Thread.currentThread().toString()+user);
//	new Thread(()->{
//		UserMapper mapper3=(UserMapper) applicationContext.getBean("userMapper");
//	    System.out.println(Thread.currentThread().toString()+mapper3);
//}).start();
	applicationContext.close();
}
}
