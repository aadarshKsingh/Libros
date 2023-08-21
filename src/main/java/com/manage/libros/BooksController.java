package com.manage.libros;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BooksController implements Initializable {
    @FXML
    private TableView<Books> adminBooksTable;
    static ObservableList<Books> booksObservableList;
    @FXML
    public TableColumn<Books, String> bname;
    @FXML
    public TableColumn<Books, String> bauthor;
    @FXML
    public TableColumn<Books, Integer> byear;
    @FXML
    public TableColumn<Books, Integer> bstocks;


    private static ObservableList<Books> bookList;

    public BooksController() {
        bookList = FXCollections.observableArrayList();
    }


    static ObservableList<Books> getBooks(ResultSet rs) throws SQLException {
        ObservableList<Books> books = FXCollections.observableArrayList();
        while(rs.next()){
           Books book = new Books(rs.getString("Book Name"),rs.getString("Author"),rs.getInt("Year"),rs.getInt("Stocks"));
            books.add(book);
            System.out.println(rs.getString("Book Name"));
            System.out.println(rs.getString("Author"));
        }
        return books;
    }



    public static void loadBooks(Connection conn) {
            String getDataQuery = "SELECT * FROM Books";
            try{
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(getDataQuery);
                booksObservableList = getBooks(rs);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        // Add more books as needed
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bname.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        bauthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        byear.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        bstocks.setCellValueFactory(new PropertyValueFactory<Books,Integer>("stocks"));
        adminBooksTable.setItems(booksObservableList);
    }
}
