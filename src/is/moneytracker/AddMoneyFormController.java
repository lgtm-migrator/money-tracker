/**
 *
 */
package is.moneytracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class AddMoneyFormController implements Initializable {
	// Reference main
	private MoneyTrackerMain mainApp;

	private FXMLLoader loader;
	private AnchorPane anchor;

	@FXML private ComboBox<String> form_trans_type;
	@FXML private ComboBox<String> form_cat;
	@FXML private TextField form_price;
	@FXML private DatePicker form_date;
	@FXML private TextArea form_note;
	@FXML private Button form_submit_btn;

	ObservableList<String> options =
		    FXCollections.observableArrayList(
		        "Option 1",
		        "Option 2",
		        "Option 3"
		    );

	/**
	 * Constructor
	 */
	public AddMoneyFormController() {
		this.loader = new FXMLLoader(getClass().getResource("view/AddMoneyForm.fxml"));
		this.loader.setController(this);
		initialController();
		try {
			this.setAnchor((AnchorPane) this.loader.load());

			this.initialController();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert form_submit_btn != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected

		this.form_trans_type.setItems(options);
		System.out.println(form_trans_type);
    }

	private void initialController() {
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
