package dtu.project.exceptions;

/**
 * default generated by eclipse when a function extends with exception
 * @author Nicholas
 *
 */
public class DuplicateActivityName extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String duplicateActivity = "Activity name already taken";
	
	
	public String toString()
	{
		return duplicateActivity;
	}
}
