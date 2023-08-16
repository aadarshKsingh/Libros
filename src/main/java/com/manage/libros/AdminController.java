package com.manage.libros;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminController {
    public TextField adminUsername;
    public TextField adminPasword;
    public Label signinError;

    public void homepage(ActionEvent actionEvent) {
    }

    public void resetFields(ActionEvent actionEvent) {
        adminUsername.clear();
        adminPasword.clear();
        signinError.setText("");
    }

    public void adminLogin(ActionEvent actionEvent) {
    }

    public void logoutAdmin(ActionEvent actionEvent) {
    }
}
