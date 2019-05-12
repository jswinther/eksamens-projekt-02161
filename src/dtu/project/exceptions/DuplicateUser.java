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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String toString() {
        return "User already exists in the selected activity";
    }
}
