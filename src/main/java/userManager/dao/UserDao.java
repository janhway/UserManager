package userManager.dao;

import java.util.List;

import userManager.entity.User;

public interface UserDao extends BaseDao<User> {

	//public void addUser(User user);
	
	//public User load(int id);

	public List<User> getUsers(String userName);
	
	public User getUser(String userName);
	
	//public List<User> getUsers();
}