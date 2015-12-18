/**
 *
 */
package is.moneytracker.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author Van-Duyet Le
 *
 */
public class Transaction {
	@Id private int id = 0;
	private int wallet_id;
	private int category_id;
	private Date created;
	private String type = "";
	private String status = "";
	private int price = 0;
	private String note = "";

	/**
	 * @param id
	 */
	public Transaction(int id) {
		super();
		this.id = id;
	}

	/**
	 *
	 */
	public Transaction() {
		super();
	}

	/**
	 * @param id
	 * @param wallet_id
	 * @param category_id
	 * @param created
	 * @param type
	 * @param status
	 * @param price
	 * @param note
	 */
	public Transaction(int id, int wallet_id, int category_id, Date created, String type, String status, int price,
			String note) {
		super();
		this.id = id;
		this.wallet_id = wallet_id;
		this.category_id = category_id;
		this.created = created;
		this.type = type;
		this.status = status;
		this.price = price;
		this.note = note;
	}

	/**
	 * @return the id
	 */
    @Id
    @GeneratedValue
	public int getId() {
		return id;
	}

	/**
	 * @return the wallet_id
	 */
	public int getWallet_id() {
		return wallet_id;
	}

	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param wallet_id the wallet_id to set
	 */
	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}

	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


}
