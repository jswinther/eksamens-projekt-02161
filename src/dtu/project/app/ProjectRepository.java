package dtu.project.app;

import java.util.List;

public interface ProjectRepository {
	List<Project> getProjectList();
	void setProjectList(List<Project> projectList);
}