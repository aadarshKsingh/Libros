package com.manage.libros;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable{
    public TextField adminUsername;
    public TextField adminPasword;
    public Label signinError;
    public static Connection conn;
    public static TableView<Books> adminBooksTable;
    public TableColumn<Books, String> bname;
    public TableColumn<Books, String> bauthor;
    public TableColumn<Books, Integer> byear;
    public TableColumn<Books, Integer> bstocks;

    boolean connected = false;
    public static FXMLLoader fxmlLoader;
    public void homepage(ActionEvent actionEvent) {
    }

    public void resetFields(ActionEvent actionEvent) {
        adminUsername.clear();
        adminPasword.clear();
        signinError.setText("");
    }

    public void adminLogin() throws IOException {
        String serverName = "localhost";
        String mydatabase = "Libros";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

        try {
            conn = DriverManager.getConnection(url,adminUsername.getText(),adminPasword.getText());
            connected = conn.isValid(1);
            System.out.println(conn);
        } catch (SQLException ignored) {

        } finally {
            if(connected){
                signinError.setText("");
                BooksController.loadBooks(conn);
                fxmlLoader = new  FXMLLoader(Main.class.getResource("adminPanel.fxml"));
                Main.getMainStage().setScene(new Scene(fxmlLoader.load()));

            }else{
                signinError.setText("Invalid Credentials or DB Inaccessible");
            }

        }
//        conn.close();
    }







        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

}
