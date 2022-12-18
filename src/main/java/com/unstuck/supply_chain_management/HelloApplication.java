package com.unstuck.supply_chain_management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createPane());
        stage.setTitle("Supply Chain Management System");
        stage.setScene(scene);
        stage.show();
    }

    public GridPane loginPageComponents(){
        Label emailLabel=new Label("Email: ");
        Label passwordLabel=new Label("Password: ");
        TextField emailTextField=new TextField();
        PasswordField passwordField=new PasswordField();//subclass TextField
        Button button=new Button("Login");
        GridPane gridPane=new GridPane();
        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(button,1,2);
        return gridPane;
    }

    public Pane createPane(){
        Pane root=new Pane();// A blank canvas where we can put the components
        root.getChildren().add(loginPageComponents());
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}