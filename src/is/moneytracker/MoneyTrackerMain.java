package is.moneytracker;

import java.io.IOException;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.service.spi.ServiceException;

import com.mysql.jdbc.CommunicationsException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import is.moneytracker.util.*;

/**
 * Main Money Tracker
 *
 * @author Van-Duyet Le
 * @version 0.1
 * @since 2015
 */
public class MoneyTrackerMain extends Application {

	private Stage primaryStage;
	private Scene mainScene;
	private BorderPane rootLayout;
	private OverviewController overview;
	private LoginForm loginForm;

	final public static boolean isLogin = false;
	private static Session session;

	public int userId = 0;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Money Tracker v0.1 - UIT");

		// Initial Main
		initRootLayout();
		showMoneyTrackerOverview();

		this.loginForm = new LoginForm();
		this.loginForm.setMainApp(this);
		try {
			this.loginForm.start(primaryStage);
			this.userId = this.loginForm.userId;
			// primaryStage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		primaryStage.show();
	}

	public boolean initConnection() {
		// Init connection
		try {
			this.session = ConnectionFactory.getSessionFactory().openSession();
		} catch (HibernateException e) {
			Message.Error("Không thể kết nối máy chủ CSDL...");
			return false;
		}

		if (session != null) return true;
		return false;
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MoneyTrackerMain.class.getResource("view/RootLayout.fxml"));
			loader.setController(this);
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			System.out.println("Initital root layout.");
			this.mainScene = new Scene(rootLayout);
			this.mainScene.getStylesheets().add("resources/main.css");
			primaryStage.setScene(this.mainScene);


			rootLayout.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>()
		    {
		        @Override
		        public void handle(WindowEvent window)
		        {
		        	System.out.println("After window showing");
		        	initConnection();
		        }
		    });


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * show Overview
	 *
	 * return
	 */
	public void showMoneyTrackerOverview() {
		overview = new OverviewController(this);
		overview.setMainApp(this);
		overview.loadTableData();
		Pane overviewPane = overview.getAnchor();

		rootLayout.setCenter(overviewPane);

		// initConnection();
	}

	public void deleteAllTrans() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Xóa toàn bộ dữ liệu");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.out.println("Stop MoneyTracker...");

			Query query = session.createQuery("DELETE FROM Transaction WHERE user_id = :user_id");
			query.setParameter("user_id", this.userId);
			query.executeUpdate();

			Message.MessageBox("Xóa thành công!");
			this.getOverviewController().loadTableData();
		}
	}

	public OverviewController getOverviewController() {
		return overview;
	}

	/**
	 * show category manager
	 *
	 * return
	 */
	public void openCategoryScene() {
		CategoryController cat = new CategoryController(this);
		cat.setMainApp(this);
		rootLayout.setCenter(cat.getAnchor());
	}

	public void openStatDashboard() {
		StatDashboardController stat = new StatDashboardController(this);
		stat.setMainApp(this);
		rootLayout.setCenter(stat.getAnchor());
	}

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	// =========================================
	// Main Action Controller
	// =========================================
	/**
	 * Closing application
	 * @throws Exception
	 */
	public void closeMoneyTracker() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Thoát?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.out.println("Stop MoneyTracker...");
			Platform.exit();
		}
	}

	/**
	 * About dialog
	 */
	public void aboutMoneyTracker() {
		Message.MessageBox("Giới thiệu",
				"Money Tracker v0.1",
				"Công cụ quản lý tài chính! \n\n\n"
				+ "Tác giả: \n"
				+ "   - Lê Văn Duyệt.\n"
				+ "   - Trần Thị Huyền Trang.\n"
				+ "   - Nguyễn Trọng Tín.\n");
	}

	public void helpMoneyTracker() {
		Message.MessageBox("Hướng dẫn",
				"Money Tracker v0.1",
				"Truy cập: http://money-tracker-java.duyetdev.com");
	}

	/**
	 * Config dialog
	 *
	 * @param args
	 */
	public void openConfigMoneyTracker() {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Cấu hình");
		dialog.setHeaderText("Cấu hình Money Tracker");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Lưu", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the config labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("mysql://localhost:3306/moneytracker");
		PasswordField password = new PasswordField();
		password.setPromptText("password...");

		grid.add(new Label("Database Path:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("DB Password:"), 0, 1);
		grid.add(password, 1, 1);

		dialog.getDialogPane().setContent(grid);

		Optional<String> result = dialog.showAndWait();
		Message.MessageBox(result.toString());
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
	    ConnectionFactory.shutdown();
	}

	/**
	 * @return the connection
	 */
	public Session getSession() {
		if (session == null) {
			this.initConnection();
		}

		return session;
	}
}
