/**
 *
 */
package is.moneytracker;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * @author Van-Duyet Le
 *
 */
public class AddMoneyFormController {
	// Reference main
	private MoneyTrackerMain mainApp;

	private FXMLLoader loader;
	private AnchorPane anchor;

	@FXML private ComboBox<String> form_trans_type;
	@FXML private TextField form_price;
	@FXML private DatePicker form_date;
	@FXML private TextArea form_note;

	/**
	 * Constructor
	 */
	public AddMoneyFormController() {
		this.loader = new FXMLLoader(getClass().getResource("view/AddMoneyForm.fxml"));
		// this.loader.setRoot(this);
		this.loader.setController(this);

		try {
			this.setAnchor((AnchorPane) this.loader.load());

			this.initialController();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private void initialController() {
		// TODO Auto-generated method stub

	}

	@FXML
	public void saveFormData(ActionEvent event) {
		System.out.println(form_price.getText());
	}

	/**
	 * @return the mainApp
	 */
	public MoneyTrackerMain getMainApp() {
		return mainApp;
	}

	/**
	 * @param mainApp the mainApp to set
	 */
	public void setMainApp(MoneyTrackerMain mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * @return the anchor
	 */
	public AnchorPane getAnchor() {
		return anchor;
	}

	/**
	 * @param anchor the anchor to set
	 */
	public void setAnchor(AnchorPane anchor) {
		this.anchor = anchor;
	}
}
