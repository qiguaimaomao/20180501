package mybatis.dao;

import java.util.List;

import mybatis.entity.User;

public interface UserMapper {
User selectUser(Integer id);

List<User> getUser();

List<User> getUserByAge(Integer age);
}
