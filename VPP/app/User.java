package app;

public abstract class User {

	private String name;
	private String identifier;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	/**
	 * 
	 * @param identifier
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * this.name = name;
	 * this.identifier = identifier;
	 * @param name
	 * @param identifier
	 */
	public User(String name, String identifier) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

}