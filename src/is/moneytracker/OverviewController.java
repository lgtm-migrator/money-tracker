/**
 *
 */
package is.moneytracker;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

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

	@FXML private AnchorPane anchor;

	@FXML private SplitPane splitPane;
	@FXML private AnchorPane leftPanel;
	@FXML private AnchorPane rightPanel;

	AddMoneyFormController addForm;
	@FXML private Button toggleAddButton;

	private FXMLLoader loader;

	@FXML private TableView<Transaction> mainTable;
	@FXML private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();

	// Table Column
	@FXML private TableColumn<Transaction, String> mainTableColumnStt;
	@FXML private TableColumn<Transaction, Long> mainTableColumnPrice;
	@FXML private TableColumn<Transaction, String> mainTableColumnNote;
	@FXML private TableColumn<Transaction, String> mainTableColumnCreated;

	/**
	 * Constructor
	 */
	public OverviewController() {
		this.loader = new FXMLLoader(getClass().getResource("view/Overview.fxml"));
		this.loader.setController(this);

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

		// Load form
		this.addForm = new AddMoneyFormController();
		this.addForm.setMainApp(mainApp);
		this.addForm.getAnchor().setMaxWidth(150);

		this.loadTableData();
	}

	public void loadTableData() {
		// Load data to table
		Session session = this.getMainApp().getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Transaction> trans = session.createQuery("FROM Transaction").list();

			transactionData = FXCollections.observableArrayList(trans);
			this.mainTable.setItems(transactionData);
			tx.commit();
		}
		catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// Hide left panel
		this.splitPane.getItems().remove(this.leftPanel);

		mainTableColumnStt.setCellValueFactory(new PropertyValueFactory<Transaction, String>("id"));
		mainTableColumnPrice.setCellValueFactory(new PropertyValueFactory<Transaction, Long>("price"));
		mainTableColumnNote.setCellValueFactory(new PropertyValueFactory<Transaction, String>("note"));
		mainTableColumnCreated.setCellValueFactory(new PropertyValueFactory<Transaction, String>("created"));

    }

	public void openAddMoneyForm() {
		if (this.splitPane.getItems().size() <= 1) {
			this.leftPanel = addForm.getAnchor();
			this.splitPane.getItems().add(0, this.leftPanel);
		}
		else {
			System.out.println("Remove left panel");
			this.splitPane.getItems().remove(0);
			this.splitPane.getItems().add(0, addForm.getAnchor());
		}
	}

	public void submitAdd() {
		// Collect data

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
