/**
 *
 */
package is.moneytracker.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Van-Duyet Le
 *
 */
public class Message {
	/**
	 * Mesage box
	 */
	public static void MessageBox(String message) {
		Message.MessageBox("Message", "", message);
	}

	public static void MessageBox(String header, String message) {
		Message.MessageBox("", message);
	}

	/**
	 * Mesage box
	 */
	public static void MessageBox(String title, String header, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header); // Disable header text
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static void Error(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(""); // Disable header text
		alert.setContentText(message);

		alert.showAndWait();
	}
}
