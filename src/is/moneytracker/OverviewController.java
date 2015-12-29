/**
 *
 */
package is.moneytracker;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.DateType;
import java.text.SimpleDateFormat;
import is.moneytracker.model.Category;
import is.moneytracker.model.Transaction;
import is.moneytracker.util.Message;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

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
	@FXML private Button deleteTransactionBtn;

// 	@FXML private BarChart<String, Integer> overviewChart;
//    @FXML private CategoryAxis xAxis;
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

	private FXMLLoader loader;

	@FXML private Label overviewIncome;
	@FXML private Label overviewOutcome;
	@FXML private Label overviewTotal;

	@FXML private ComboBox<Integer> pickerYear;
	@FXML private ComboBox<Integer> pickerMonth;

	Long _in = (long) 0;
	Long _out = (long) 0;
	Long _total = (long) 0;

	@FXML private TableView<Transaction> mainTable;
	@FXML private ObservableList<Transaction> transactionData = FXCollections.observableArrayList();
	Transaction selectedItem;

	// Table Column
//	@FXML private TableColumn<Transaction, String> mainTableColumnStt;
	@FXML private TableColumn<Transaction, Long> mainTableColumnPrice;
	@FXML private TableColumn<Transaction, String> mainTableColumnNote;
	@FXML private TableColumn<Transaction, Date> mainTableColumnCreated;

	protected Integer currentMonth;

	protected Integer currentYear;

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
		this.addForm = new AddMoneyFormController(mainApp);
		this.addForm.getAnchor().setMaxWidth(150);

		this.loadTableData();
	}

	public void loadTableData() {
		loadTableData(-1, -1);
	}

	public void loadTableData(int month, int year) {
		// Load data to table
		Session session = this.getMainApp().getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Transaction> trans;
			if (month > 0 && year > 0) {
				trans = session.createQuery("FROM Transaction WHERE MONTH(created) = :month AND YEAR(created) = :year")
						.setParameter("month", month)
						.setParameter("year", year)
						.list();
			} else {
				trans = session.createQuery("FROM Transaction").list();
			}

			transactionData = FXCollections.observableArrayList(trans);
			this.mainTable.setItems(transactionData);
			updateOverViewStat(transactionData);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		// Hide left panel
		this.splitPane.getItems().remove(this.leftPanel);

//		mainTableColumnStt.setCellValueFactory(new PropertyValueFactory<Transaction, String>("id"));
		mainTableColumnPrice.setCellValueFactory(new PropertyValueFactory<Transaction, Long>("price"));
		mainTableColumnNote.setCellValueFactory(new PropertyValueFactory<Transaction, String>("note"));
		mainTableColumnCreated.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("created"));

		overviewIncome.setText("0");
		overviewOutcome.setText("0");
		overviewTotal.setText("0");

		ObservableList<Integer> months =
			    FXCollections.observableArrayList(
			        1,2,3,4,5,6,7,8,9,10,11,12
			    );
		currentMonth = new Date().getMonth() + 1;
		pickerMonth.setItems(months);
		pickerMonth.setValue(currentMonth);

		ObservableList<Integer> years =
			    FXCollections.observableArrayList(
			        2013,2014,2015,2016
			    );
		currentYear = 2015;
		pickerYear.setItems(years);
		pickerYear.setValue(currentYear);

		OverviewController that = this;
		pickerMonth.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
               that.currentMonth  = t1;
               that.loadTableData(that.currentMonth, that.currentYear);
            }
        });
		pickerYear.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
               that.currentYear  = t1;
               that.loadTableData(that.currentMonth, that.currentYear);
            }
        });

		deleteTransactionBtn.setDisable(this.selectedItem == null);
	    mainTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
	        @Override
	        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
	            //Check whether item is selected and set value of selected item to Label
	            if(mainTable.getSelectionModel().getSelectedItem() != null)
	            {
	               TableViewSelectionModel<Transaction> selectionModel = mainTable.getSelectionModel();
	               that.selectedItem = selectionModel.getSelectedItem();
	               deleteTransactionBtn.setDisable(that.selectedItem == null);
	               System.out.println("Selected Value" + that.selectedItem.getId());
	             } else {
	            	 that.selectedItem = null;
	             }
	             }
	         });

//	    // Get an array with the English month names.
//        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
//		// Convert it to a list and add it to our ObservableList of months.
//		monthNames.addAll(Arrays.asList(months));
//
//		// Assign the month names as categories for the horizontal axis.
//        xAxis.setCategories(monthNames);


    }

	public void updateOverViewStat(ObservableList<Transaction> transactionData) {
		transactionData.listIterator();
		overviewIncome.setText("0");
		overviewOutcome.setText("0");
		overviewTotal.setText("0");

		_out = 0L;
		_in = 0L;
		_total = 0L;

		transactionData.forEach((trans) -> {
			if (trans.getPrice() < 0) _out += trans.getPrice();
			else _in += (long) trans.getPrice();
			_total += (long) trans.getPrice();
		});

		overviewIncome.setText(_in.toString() + " VNĐ");
		overviewOutcome.setText(_out.toString() + " VNĐ");
		overviewTotal.setText(_total.toString() + " VNĐ");
	}

    /**
     * Sets the persons to show the statistics for.
     *
     * @param persons
     */
    public void setPersonData(List<Transaction> trans) {
//        // Count the number of people having their birthday in a specific month.
//        int[] monthCounter = new int[12];
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//        for (Transaction t : trans) {
//
//
//			int month = t.getCreated().getMonth() - 1;
//            monthCounter[month]++;
//        }
//
//        XYChart.Series<String, Integer> series = new XYChart.Series<>();
//
//        // Create a XYChart.Data object for each month. Add it to the series.
//        for (int i = 0; i < monthCounter.length; i++) {
//            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
//        }
//
//        overviewChart.getData().add(series);
    }


	public void deleteTransaction() {
		if (this.selectedItem != null) {
			Session session = this.getMainApp().getSession();
	        org.hibernate.Transaction tx = null;
	        try {
	        	tx = session.beginTransaction();
				Transaction cat = session.load(Transaction.class, this.selectedItem.getId());
				session.delete(cat);
				tx.commit();

				Message.MessageBox("Xoá thành công");
				loadTableData();
	        } catch (HibernateException e) {
	            Message.Error(e.getMessage());
	            e.printStackTrace();
	         }

		} else {
			Message.Error("Vui lòng chọn dòng để xoá");
		}
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
