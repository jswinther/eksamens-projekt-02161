package dtu.project.repo;

import dtu.project.app.TimePeriod;
import dtu.project.app.Event;
import dtu.project.app.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
	Map<User, List<Event>> getUserMap();
}