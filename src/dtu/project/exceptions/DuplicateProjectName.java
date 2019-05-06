package dtu.project.exceptions;

public class DuplicateProjectName extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String duplicateProject = "Project name already taken";
	
	
	public String toString()
	{
		return duplicateProject;
	}

}
