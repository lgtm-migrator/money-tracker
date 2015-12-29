/**
 *
 */
package is.moneytracker.model;

/**
 * @author Van-Duyet Le
 *
 */
public class TransactionByCategory {
	private int id = 0;
	private String name = "";
	private int income = 0;
	private int outcome = 0;
	private int total = 0;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the income
	 */
	public int getIncome() {
		return income;
	}
	/**
	 * @return the outcome
	 */
	public int getOutcome() {
		return outcome;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param income the income to set
	 */
	public void setIncome(int income) {
		this.income = income;
	}
	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(int outcome) {
		this.outcome = outcome;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @param name
	 * @param income
	 * @param outcome
	 * @param total
	 */
	public TransactionByCategory(String name, int income, int outcome, int total) {
		super();
		this.name = name;
		this.income = income;
		this.outcome = outcome;
		this.total = total;
	}

	public TransactionByCategory()
	{}

}
