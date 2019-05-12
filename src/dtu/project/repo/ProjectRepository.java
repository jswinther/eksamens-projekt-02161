package dtu.project.repo;

import java.util.List;

import dtu.project.entities.Project;
/**
 * The interface is a contract used with all repossitories, so the database can only recieve controlled input.
 * This wouldn't have been necessary if the database was written in SQL, however, this path was chosen.
 * @author Nicholas
 *
 */
public interface ProjectRepository {
	List<Project> getProjectList();
}