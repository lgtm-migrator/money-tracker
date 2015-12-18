/**
 *
 */
package is.moneytracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.mapping.List;
import org.hibernate.type.DateType;

import is.moneytracker.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * @author Van-Duyet Le
 *
 */
/**
 * @author Van-Duyet Le
 *
 */
public class CategoryController implements Initializable {

	// Reference main
	private MoneyTrackerMain mainApp;

	@FXML private AnchorPane anchor;

	@FXML private Button back_to_main_btn;

	@FXML private ComboBox<String> cat_type;
	@FXML private TextField cat_name;
	@FXML private Button cat_submit;

	private FXMLLoader loader;

	@FXML private TableView<Transaction> mainTable;
	@FXML private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();

	// Table Column
	@FXML private TableColumn<String, String> mainTableColumnStt;
	@FXML private TableColumn<String, String> mainTableColumnPrice;
	/**
	 * Constructor
	 */
	public CategoryController() {
		this.loader = new FXMLLoader(getClass().getResource("view/Category.fxml"));
		this.loader.setController(this);

		// setMainTableData();

		try {
			this.anchor = (AnchorPane) this.loader.load();

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public CategoryController(MoneyTrackerMain mainApp) {
		this();
		this.setMainApp(mainApp);
	}

	public void setMainTableData() {
		if (mainApp == null) return;

		// Session s = this.mainApp.getSesion().getSession();

	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		//this.openAddMoneyForm();
    }

	public void submitNewCat() {

	}

	/**
	 * Get Parent Scene
	 * @return Scene
	 */
	public Scene getParentScene() {
		return this.getMainApp().getMainScene();
	}

	public AnchorPane getAnchor() {
		return this.anchor;
	}

	/**
	 * Is called by main --> reference back to itself
	 */
	public void setMainApp(MoneyTrackerMain main) {
		this.mainApp = main;
	}
	public MoneyTrackerMain getMainApp() {
		return this.mainApp;
	}

	public void backToMain() {
		this.getMainApp().showMoneyTrackerOverview();
	}

	// ================================
	// Context menu
	// ================================
	/**
	 * Add record
	 */
	public void addAction() {

	}


	public void exitAction() throws Exception {
		this.getMainApp().closeMoneyTracker();
	}

	public void aboutAction() {
		System.out.println("About...");
		this.getMainApp().aboutMoneyTracker();
	}
}
