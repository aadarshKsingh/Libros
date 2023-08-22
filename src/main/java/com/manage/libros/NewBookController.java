package com.manage.libros;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewBookController {
    public Button newBookCancel;
    public Button newBookAdd;
    public Label newBookError;
    public TextField newBookStocks;
    public TextField newBookYear;
    public TextField newBookAuthor;
    public TextField newBookName;

    public static Connection connection;
    void addBook(Connection conn){
        connection = conn;
        try {
            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newBook.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Book2DB(ActionEvent actionEvent) throws SQLException {
        PreparedStatement newBook = connection.prepareStatement("INSERT INTO Books VALUES(?,?,?,?)");
        newBook.setString(1,newBookName.getText());
        newBook.setString(2,newBookAuthor.getText());
        newBook.setInt(3,Integer.parseInt(newBookYear.getText()));
        newBook.setInt(4,Integer.parseInt(newBookStocks.getText()));
        newBook.execute();
        ((Stage)newBookYear.getScene().getWindow()).close();



    }

    public void cancelNewBook(ActionEvent actionEvent) {
        ((Stage)newBookYear.getScene().getWindow()).close();
    }
}
