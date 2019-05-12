package dtu.project.repo;

import java.util.List;

import dtu.project.entities.Project;
/**
 * 
 * @author Nicholas
 *
 */
public interface ProjectRepository {
	List<Project> getProjectList();
}