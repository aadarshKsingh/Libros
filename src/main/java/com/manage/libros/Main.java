package com.manage.libros;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static FXMLLoader fxmlLoader;
    static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        fxmlLoader = new FXMLLoader(Main.class.getResource("libros.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public void adminLogin(ActionEvent actionEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("adminLogin.fxml"));
        mainStage.setScene(new Scene(fxmlLoader.load()));

    }
    public void userLogin(ActionEvent actionEvent) throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("userLogin.fxml"));
        mainStage.setScene(new Scene(fxmlLoader.load()));
    }
}