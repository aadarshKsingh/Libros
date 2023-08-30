package com.manage.libros;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BooksUserController implements Initializable {
    public static Connection connection;
    public static FXMLLoader fxmlLoader;
    @FXML
    public TableColumn<Books, String> bnameUser;
    @FXML
    public TableColumn<Books, String> bauthorUser;
    @FXML
    public TableColumn<Books, Integer> byearUser;
    @FXML
    public TableColumn<Books, Integer> bstocksUser;
    public TableView<Books> userAllTables;
    private static ObservableList<Books> bookList;
    static ObservableList<Books> booksObservableList;
    public BooksUserController(){ bookList = FXCollections.observableArrayList(); }

    // loads books and set to table to show available books
    //------------------WIP-----------------//
    public static void loadBooks(Connection conn) {
        String getDataQuery = "SELECT * FROM Books";
        connection = conn;
        booksObservableList = FXCollections.observableArrayList();
        try{
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(getDataQuery);
            while(rs.next()){
                Books book = new Books(rs.getString("Book Name"),rs.getString("Author"),rs.getInt("Year"),rs.getInt("Stocks"));
                booksObservableList.add(book);
                System.out.println(rs.getString("Book Name"));
                System.out.println(rs.getString("Author"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ////////////////////////

    // closes connection and loads home scene
    public void logoutUser() {
        try {
            connection.close();
            fxmlLoader = new FXMLLoader(Main.class.getResource("libros.fxml"));
            Main.getMainStage().setScene(new Scene(fxmlLoader.load()));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // supposed to initialize table to show available books
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bnameUser.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        bauthorUser.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        byearUser.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        bstocksUser.setCellValueFactory(new PropertyValueFactory<Books,Integer>("stocks"));
        userAllTables.setItems(booksObservableList);
    }
}
