package com.manage.libros;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class UserController {
    public TextField userPassword;
    public TextField userUsername;
    public FXMLLoader fxmlLoader;
    public void homepage(ActionEvent actionEvent) throws IOException {
     fxmlLoader = new FXMLLoader(Main.class.getResource("libros.fxml"));
      Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    public void resetFields(ActionEvent actionEvent) {
    }

    public void loginUser(ActionEvent actionEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("userPanel.fxml"));
        Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    public void signUp(MouseEvent mouseEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("userSignup.fxml"));
        Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }

    public void userSignin(MouseEvent mouseEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("userLogin.fxml"));
        Main.mainStage.setScene(new Scene(fxmlLoader.load()));
    }
}
