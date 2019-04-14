package dtu.project.repo;

import dtu.project.app.User;

import java.util.List;

public interface UserRepository {
	List<User> getUserList();
	void setUserList(List<User> userList);
}