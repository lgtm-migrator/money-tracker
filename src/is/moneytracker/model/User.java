/**
 *
 */
package is.moneytracker.model;

/**
 * @author Van-Duyet Le
 *
 */
public class User {
	private int id = 0;
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
	public User(int id, String name, String username, String password, String status) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.status = status;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param name the name to set
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
	 * @param username the username to set
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
	 * @param password the password to set
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
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
