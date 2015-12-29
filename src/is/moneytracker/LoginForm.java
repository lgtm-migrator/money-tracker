package is.moneytracker;

import java.io.IOException;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SharedSessionContract;

import is.moneytracker.model.Transaction;
import is.moneytracker.model.User;
import is.moneytracker.util.Message;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class LoginForm extends Application {
	final public static boolean isLogin = false;
	public int userId = 0;
	Optional<Pair<String,String>> result;

	MoneyTrackerMain mainApp;
	private SharedSessionContract session;

	@Override
    public void start(Stage primaryStage) throws Exception {
        Dialog<Pair<String,String>> dlg = new Dialog<>();
        dlg.setTitle("Login Form");
        dlg.setHeaderText("Nhập tên tài khoản và mật khẩu để đăng nhập");

        ButtonType loginBtnType = new ButtonType("Login");
        dlg.getDialogPane().getButtonTypes().addAll(loginBtnType,ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("User Name");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Password");
        Label lblUserName = new Label("User Name");
        Label lblPassword = new Label("Password");
        Reflection reflection = new Reflection();
        reflection.setFraction(0.9);
        lblUserName.setEffect(reflection);
        lblPassword.setEffect(reflection);
        grid.add(lblUserName, 0, 0);
        grid.add(lblPassword, 0, 1);
        grid.add(txtUsername, 2, 0);
        grid.add(txtPassword, 2, 1);
        Node loginButton = dlg.getDialogPane().lookupButton(loginBtnType);
        dlg.getDialogPane().setContent(grid);
        loginButton.setDisable(true);
        txtUsername.textProperty().addListener((Observable,oldValue,newValue)->{
          loginButton.setDisable(newValue.trim().isEmpty());
        });

        dlg.setResultConverter((ButtonType dialogButton) -> {
            if(dialogButton==loginBtnType){
                return new Pair<>(txtUsername.getText(),txtPassword.getText());
            }
            else if(dialogButton==ButtonType.CANCEL){

                return null;
            } else{
                return null;
            }
        });

		try {
			this.session = ConnectionFactory.getSessionFactory().openSession();
		} catch (HibernateException e) {
			Message.Error("Không thể kết nối máy chủ CSDL...");
			return;
		}

        LoginForm that = this;
        this.result = dlg.showAndWait();
        result.ifPresent(usernamePassword->{
        	System.out.println("user name : "+
                usernamePassword.getKey()+"\n Password : "+usernamePassword.getValue());
        	User u = null;
            org.hibernate.Transaction tx = null;

        	try {
				tx = session.beginTransaction();
    			Query query = session.createQuery("FROM User WHERE username = :username");
    			query.setParameter("username", usernamePassword.getKey());

    			if (query.list().size() == 0) {
    				Message.Error("Sai tài khoản hoặc mật khẩu!");
//    				ConnectionFactory.shutdown();
            		this.result = dlg.showAndWait();
            		return;
    			}

				u = (User)query.list().get(0);

				tx.commit();
            } catch (HibernateException e) {
                Message.Error(e.getMessage());
                e.printStackTrace();
//                ConnectionFactory.shutdown();
             }

        	if (u != null && u.getPassword().equals(usernamePassword.getValue())) {
        		// primaryStage.show();
        		this.userId = (int) u.getId();
        		System.out.println("Login success");
//        		ConnectionFactory.shutdown();
        		primaryStage.show();
        	} else {
        		Message.Error("Sai tài khoản hoặc mật khẩu!");
//        		ConnectionFactory.shutdown();
        		this.result = dlg.showAndWait();

        	}
        });
    }

    public int getUserId() {
    	return this.userId;
    }

    public void setMainApp(MoneyTrackerMain main) {
    	this.mainApp = main;
    }
}
