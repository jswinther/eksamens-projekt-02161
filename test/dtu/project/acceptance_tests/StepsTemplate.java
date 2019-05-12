package dtu.project.acceptance_tests;

import dtu.project.controllers.ProjectApp;
import dtu.project.repo.InMemoryRepository;

public class StepsTemplate {
	
	protected ProjectApp PA;
	
	public StepsTemplate(InMemoryRepository MP) {
		this.PA = new ProjectApp(MP, MP);
	}

}
