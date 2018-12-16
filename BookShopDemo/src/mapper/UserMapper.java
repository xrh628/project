package mapper;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserMapper {
	User selectUserBynp(@Param("username")String username,@Param("password")String password);
	public void addUser(User user);
}
