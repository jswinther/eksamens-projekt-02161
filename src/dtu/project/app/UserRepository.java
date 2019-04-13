package dtu.project.app;

import java.util.List;

public interface UserRepository {
	List<User> getUserList();
	void setUserList(List<User> userList);
}