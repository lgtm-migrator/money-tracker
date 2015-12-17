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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * @author Van-Duyet Le
 *
 */
/**
 * @author Van-Duyet Le
 *
 */
public class OverviewController implements Initializable {

	// Reference main
	private MoneyTrackerMain mainApp;

	private AnchorPane anchor;

	@FXML private SplitPane splitPane;
	@FXML private AnchorPane leftPanel;
	@FXML private AnchorPane rightPanel;

	private FXMLLoader loader;

	@FXML private TableView<Transaction> mainTable;
	@FXML private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();

	// Table Column
	@FXML private TableColumn<String, String> mainTableColumnStt;
	@FXML private TableColumn<String, String> mainTableColumnPrice;
	/**
	 * Constructor
	 */
	public OverviewController() {
		this.loader = new FXMLLoader(getClass().getResource("view/Overview.fxml"));
		this.loader.setController(this);

		setMainTableData();

		try {
			this.anchor = (AnchorPane) this.loader.load();
			this.splitPane = (SplitPane) this.anchor.getChildren().get(0);

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public OverviewController(MoneyTrackerMain mainApp) {
		this();
		this.setMainApp(mainApp);
	}

	public void setMainTableData() {
		if (mainApp == null) return;

		Session s = this.mainApp.getConnection().getSession();

	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		this.openAddMoneyForm();
    }

	public void openAddMoneyForm() {
		// Load default left panel
		AddMoneyFormController addForm = new AddMoneyFormController();
		addForm.setMainApp(mainApp);
		this.setLeftPanel((AnchorPane) addForm.getAnchor());
	}

	/**
	 * Get loaded FXMLLoader
	 * @return Object
	 */
	public AnchorPane getAnchor() {
		this.getLeftPanel();
		return this.anchor;
	}

	/**
	 * Get SplitPane
	 *
	 * @return
	 */
	public SplitPane getSplitPane() {
		return this.splitPane;
	}

	/**
	 * Get Parent Scene
	 * @return Scene
	 */
	public Scene getParentScene() {
		return this.getMainApp().getMainScene();
	}

	public Node getLeftPanel() {
		return this.leftPanel;
	}

	public Node setLeftPanel(Node anchor) {
		return this.splitPane.getItems().set(0, anchor);
	}

	public Node setMainPanel(Node anchor) {
		return this.splitPane.getItems().set(1, anchor);
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

	// ================================
	// Context menu
	// ================================
	/**
	 * Add record
	 */
	public void addAction() {
		this.openAddMoneyForm();
	}

	public void exitAction() throws Exception {
		this.getMainApp().closeMoneyTracker();
	}

	public void aboutAction() {
		System.out.println("About...");
		this.getMainApp().aboutMoneyTracker();
	}
}
