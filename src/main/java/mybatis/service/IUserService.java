package mybatis.service;

import java.util.List;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import mybatis.entity.User;

public interface IUserService {
User getUser(Integer id);

List<User> getUserByCursor() throws UnexpectedInputException, ParseException, Exception;

List<User> getUserByAge(Integer age) throws UnexpectedInputException, ParseException, Exception;
}
