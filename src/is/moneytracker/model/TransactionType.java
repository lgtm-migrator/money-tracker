/**
 *
 */
package is.moneytracker.model;

/**
 * @author Van-Duyet Le
 *
 */
public class TransactionType {
	private String id = "";
	private String name = "";

	@Override
    public String toString() {
        return name;
    }

	/**
	 * @param id
	 * @param name
	 */
	public TransactionType(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TransactionType(String type) {
		this.id = type;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
