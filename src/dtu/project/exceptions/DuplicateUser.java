/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.project.exceptions;

/**
 *
 * @author Sebastian
 */
public class DuplicateUser extends Exception {
    /**
	 * Exception thrown when trying to add the same user to an activity they're already part of.
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
        return "User already exists in the selected activity";
    }
}
