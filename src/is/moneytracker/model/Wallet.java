/**
 *
 */
package is.moneytracker.model;

/**
 * @author Van-Duyet Le
 *
 */
public class Wallet {
	private int id;
	private int user_id;
	private String name = "";
	private String status = "";

	/**
	 * @param id
	 * @param user_id
	 * @param name
	 * @param status
	 */
	public Wallet(int id, int user_id, String name, String status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.status = status;
	}

	/**
	 * @param id
	 */
	public Wallet(int id) {
		super();
		this.id = id;
	}

	/**
	 *
	 */
	public Wallet() {
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
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user_id
	 */
	public long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

}
