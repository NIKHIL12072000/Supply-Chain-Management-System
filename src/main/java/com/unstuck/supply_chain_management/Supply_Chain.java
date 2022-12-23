package com.unstuck.supply_chain_management;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Supply_Chain extends Application {

    public static final int height=700, width=700, headerbar=50;
    Pane bodyPane=new Pane();
    Product_Details productDetails=new Product_Details();

    private GridPane headerPane(){
            TextField textField=new TextField();
            Button button=new Button("Search");
            GridPane gridPane=new GridPane();
            gridPane.setVgap(15);
            gridPane.setHgap(10);
            gridPane.setAlignment(Pos.TOP_CENTER);
            gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
            gridPane.setStyle("-fx-background-color: #000000");
            gridPane.add(textField,0,1);
            gridPane.add(button,1,1);
            return gridPane;
    }
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
        Label buttonLabel =new Label(" ");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailTextField.getText().trim().toString();
                String password=passwordField.getText().toString();
                Login login=new Login();
                if(login.customerLogin(email,password))
                    buttonLabel.setText("You have successfully logged in");
                else
                    buttonLabel.setText("Your Account Doesnot exists. Please Register");
            }
        });
        GridPane gridPane=new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(button,1,2);
        gridPane.add(buttonLabel,1,3);
        gridPane.setStyle("-fx-background-color: #ed872d");
        return gridPane;
    }

    public Pane createPane(){
        Pane root=new Pane();// A blank canvas where we can put the components

        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerbar);
        bodyPane.getChildren().addAll(loginPageComponents());
        bodyPane.getChildren().addAll(productDetails.getAllProducts());

        root.setPrefSize(width,height+headerbar);
        root.getChildren().addAll(headerPane(), bodyPane);

        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}