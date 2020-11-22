package mybatis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mybatis.dao.UserMapper;
import mybatis.entity.User;
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MyBatisCursorItemReader<User> myBatisCursorItemReader;
	
	@Override
	public User getUser(Integer id) {
		return userMapper.getUser().get(0);
	}

	
	public List<User> getUserByCursor() throws UnexpectedInputException, ParseException, Exception {
		List<User> users=new ArrayList<User>();
		myBatisCursorItemReader.setQueryId("mybatis.dao.UserMapper.getUser");
		myBatisCursorItemReader.open(new ExecutionContext());
		User userVo;
		while ((userVo = myBatisCursorItemReader.read()) != null) {
			System.out.println(userVo);
			users.add(userVo);
		}
		myBatisCursorItemReader.close();
		return users;
	}


	@Override
	public List<User> getUserByAge(Integer age) throws UnexpectedInputException, ParseException, Exception {
		List<User> users=new ArrayList<User>();
		myBatisCursorItemReader.setQueryId("mybatis.dao.UserMapper.getUserByAge");
		Map<String, Object> parameterValues=new HashMap<String, Object>();
		parameterValues.put("age", age);
		myBatisCursorItemReader.setParameterValues(parameterValues);
		myBatisCursorItemReader.open(new ExecutionContext());
		
		User userVo;
		while ((userVo = myBatisCursorItemReader.read()) != null) {
			System.out.println(userVo);
			users.add(userVo);
		}
		myBatisCursorItemReader.close();
		return users;
	}
}
