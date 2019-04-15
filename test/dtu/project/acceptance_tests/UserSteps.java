package dtu.project.acceptance_tests;

import dtu.project.app.ProjectApp;
import dtu.project.repo.InMemoryRepository;

public class UserSteps {

	ProjectApp PA;

	public UserSteps(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}


}

