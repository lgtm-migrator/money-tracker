/**
 *
 */
package is.moneytracker.model;

import javax.persistence.Id;

/**
 * @author Van-Duyet Le
 *
 */
public class User {
	@Id
	private long id;

	private String name = "";
	private String username = "";
	private String password = "";
	private String status = "";

	public User() {

	}

	/**
	 * @param id
	 * @param name
	 * @param username
	 * @param password
	 * @param status
	 */
	public User(String name, String username, String password, String status) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public static int login(String username, String password) {

		User userLogin = new User();

		return 0;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public User clone() {
		return new User(name, username, password, status);
	}
}
