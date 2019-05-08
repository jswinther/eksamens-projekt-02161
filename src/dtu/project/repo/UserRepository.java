package dtu.project.repo;

import java.util.List;
import java.util.Map;

import dtu.project.entities.Event;
import dtu.project.entities.TimePeriod;
import dtu.project.entities.User;

public interface UserRepository {
	Map<User, List<Event>> getUserMap();
}