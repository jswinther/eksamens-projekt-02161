package dtu.project.repo;

import java.util.List;

import dtu.project.entities.Project;

public interface ProjectRepository {
	List<Project> getProjectList();
}