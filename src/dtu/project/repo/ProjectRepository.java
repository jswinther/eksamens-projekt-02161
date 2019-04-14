package dtu.project.repo;

import dtu.project.app.Project;

import java.util.List;

public interface ProjectRepository {
	List<Project> getProjectList();
	void setProjectList(List<Project> projectList);
}