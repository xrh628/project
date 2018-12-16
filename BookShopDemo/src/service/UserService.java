package service;

import org.springframework.stereotype.Service;

import entity.User;
import mapper.UserMapper;
@Service
public class UserService {
	UserMapper userMapper;
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
     public 	 User findUser(String username,String password) {
		 User user=null;
		  user=userMapper.selectUserBynp(username, password);
		 return user;
	 }
     
     public void addUser(User user) {
    	 userMapper.addUser(user);
     }
	
}
