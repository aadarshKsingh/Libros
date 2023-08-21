package com.manage.libros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserController{

    public TextField signupUsername;
    public TextField signupAge;
    public TextField signupMail;
    public TextField signupPassword;
    public Label signupError;
    public Button signupButton;
    public TextField loginUsername;
    public TextField loginPassword;
    public Label loginError;
    FXMLLoader fxmlLoader;
    public void homepage(ActionEvent actionEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("libros.fxml"));
        Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    static Connection conn;

    public void resetFields(ActionEvent actionEvent) {
        signupUsername.setText("");
        signupAge.setText("");
        signupMail.setText("");
        signupPassword.setText("");
        signupError.setText("");
        loginUsername.setText("");
        loginPassword.setText("");
        loginError.setText("");
    }


    public void loginUser(ActionEvent actionEvent){
        String serverName = "localhost";
        String mydatabase = "Libros";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        System.out.println(loginUsername.getText());
        System.out.println(loginPassword.getText());
       try{
           conn = DriverManager.getConnection(url, loginUsername.getText(), loginPassword.getText());
           if(conn.isValid(1)){
               fxmlLoader = new FXMLLoader(Main.class.getResource("userPanel.fxml"));
               Main.mainStage.setScene(new Scene(fxmlLoader.load()));
           }else{
               loginError.setText("Invalid credentials or inaccesible database");
           }
       }catch (IOException | SQLException e){
           e.printStackTrace();
        }

    }


    public boolean validate() {
        if (signupUsername.getText().contains(" ") || signupPassword.getText().contains(" ") || signupMail.getText().contains(" ") || signupAge.getText().contains(" ")) {
            signupError.setText("Fields can't contain spaces");
            return false;
        } else if (signupPassword.getLength() < 8) {
            signupError.setText("Password should be longer than 8 chars");
            return false;
        } else if (!signupMail.getText().contains("@") || signupMail.getText().endsWith(Arrays.toString(new String[]{".edu", ".com", ".in", ".org"}))) {
            signupError.setText("Enter valid mail");
            return false;
        } else if (signupMail.getText().isEmpty() || signupAge.getText().isEmpty() || signupUsername.getText().isEmpty() || signupPassword.getText().isEmpty()) {
            signupError.setText("All fields are mandatory");
            return false;
        }
        return true;
    }

    public void signUp(ActionEvent mouseEvent) {
        String serverName = "localhost";
        String mydatabase = "Libros";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;


        boolean status = false;
        try {
            if(validate()){
                conn = DriverManager.getConnection(url, "UserManager", "password");
                String createUAquery = "CREATE USER '" + signupUsername.getText()+"'@'"+serverName + "' IDENTIFIED BY '" + signupPassword.getText()+"'";
                String createTablequery = "CREATE TABLE " + signupUsername.getText() + "(BookName VARCHAR(20),Author VARCHAR(20),Year INT(4))";
                String grantPrivquery = "GRANT SELECT,UPDATE on "+mydatabase+"."+signupUsername.getText()+" TO "+signupUsername.getText()+"@"+serverName;

                PreparedStatement updateUsers = conn.prepareStatement("INSERT INTO Users VALUES(?,?,?,?,?)");
                Statement createTable = conn.createStatement();
                createTable.execute(createTablequery);
                Statement createUA = conn.createStatement();
                createUA.execute(createUAquery);
                Statement grantPriv = conn.createStatement();
                grantPriv.execute(grantPrivquery);
                updateUsers.setString(1, signupUsername.getText());
                updateUsers.setString(2, signupPassword.getText());
                updateUsers.setInt(3, Integer.parseInt(signupAge.getText()));
                updateUsers.setString(4, signupMail.getText());
                updateUsers.setInt(5, Integer.parseInt("0"));
                updateUsers.execute();
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (status) {
                signupError.setText("Signup Successful! You can login now.");
            }
            else
                signupError.setText("Already exists");
        }

    }

    public void goToSignup(MouseEvent mouseEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("userSignup.fxml"));
        Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    public void goToSignin(MouseEvent mouseEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("userLogin.fxml"));
        Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    public void logoutUser(ActionEvent actionEvent) {
        try {
            conn.close();
            fxmlLoader = new FXMLLoader(Main.class.getResource("libros.fxml"));
            Main.getMainStage().setScene(new Scene(fxmlLoader.load()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


