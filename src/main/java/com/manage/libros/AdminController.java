package com.manage.libros;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
public class AdminController {
    public TextField adminUsername;
    public TextField adminPasword;
    public Label signinError;
    public static Connection conn;
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
                fxmlLoader = new  FXMLLoader(Main.class.getResource("adminPanel.fxml"));
                Main.getMainStage().setScene(new Scene(fxmlLoader.load()));
            }else{
                signinError.setText("Invalid Credentials or DB Inaccessible");
            }

        }
//        conn.close();
    }

    public void logoutAdmin(){
        try {
            conn.close();
          fxmlLoader = new FXMLLoader(Main.class.getResource("libros.fxml"));
          Main.getMainStage().setScene(new Scene(fxmlLoader.load()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
