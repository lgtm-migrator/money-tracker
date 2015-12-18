/**
 *
 */
package is.moneytracker.model;

/**
 * @author Van-Duyet Le
 *
 */
public class Category {
	private long id;
	private String name = "";
	private String type = "";
	private String image = "";
	private String description = "";

	/**
	 * @param id
	 */
	public Category(long id) {
		super();
		this.id = id;
	}

	/**
	 *
	 */
	public Category() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param image
	 * @param description
	 */
	public Category(String name, String type, String image, String description) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
	}

	@Override
	public String toString() {
		return this.name;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
