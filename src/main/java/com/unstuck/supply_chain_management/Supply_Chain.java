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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Supply_Chain extends Application {

    public static final int height=700, width=700, headerbar=50;
    Pane bodyPane=new Pane();
    Pane root=new Pane();
    Product_Details productDetails=new Product_Details();
    Login login=new Login();
    TextField textField=new TextField();
    Button button=new Button("Search");
    Button loginButton=new Button("Login");
    Label loginStatus=new Label("Log In? Please Click on the button on left");


    private GridPane headerPane(){
        loginStatus.setTextFill(Paint.valueOf("FFA500"));

        button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(!login.isLoggedIn)
                    loginStatus.setText("Log In? Please Click on the button on left");
                    else
                        loginStatus.setText("Welcome "+login.getCustomerName());
                    String productName=textField.getText();
                    productDetails.getProductByName(productName);
                    bodyPane.getChildren().clear();
                    root.getChildren().get(2).setVisible(true);
                    bodyPane.getChildren().add(productDetails.getProductByName(productName));
                }
            });

            if(login.isLoggedIn)
                loginButton.setVisible(false);
            loginButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    loginStatus.setText("Please enter your details");
                    bodyPane.getChildren().clear();
                    root.getChildren().get(2).setVisible(false);
                    root.setBackground(Background.fill(Paint.valueOf("#000000")));
                    bodyPane.getChildren().addAll(loginPageComponents());
                }
            });
            GridPane gridPane=new GridPane();
            gridPane.setVgap(15);
            gridPane.setHgap(10);
            gridPane.setAlignment(Pos.TOP_CENTER);
            gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
            gridPane.setStyle("-fx-background-color: #000000");
            gridPane.add(textField,0,1);
            gridPane.add(button,1,1);
            gridPane.add(loginButton,2,1);
            gridPane.add(loginStatus,3,1);
            return gridPane;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createPane());
        stage.setTitle(" ");
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
                if(login.customerLogin(email,password)) {
                    buttonLabel.setText("You have successfully logged in");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().addAll(productDetails.getAllProducts());
                    if(login.isLoggedIn){
                        loginButton.setVisible(false);
                        root.getChildren().get(2).setVisible(true);
                    }
                    loginStatus.setTextFill(Paint.valueOf("FFA500"));
                    loginStatus.setText("Welcome "+login.getCustomerName());
                }
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
        // A blank canvas where we can put the components

        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerbar);
        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.setPrefSize(width,height+2*headerbar);
        root.getChildren().addAll(headerPane(), bodyPane, footerPane());

        return root;
    }

    private GridPane footerPane(){
        Button addToCart=new Button("Add to Cart");
        Button buyNow=new Button("Buy Now");
        Label orderStatus=new Label("Your Cart is Empty");

        orderStatus.setTextFill(Paint.valueOf("FFA500"));
        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct=productDetails.getSelectedProduct();
                if(login.isLoggedIn){
                    System.out.println(selectedProduct.getName());
                    if(Order.placeOrder(login.getCustomerEmail(),selectedProduct)){
                        System.out.println(selectedProduct.getName());
                        orderStatus.setText("Your Order Has Been Placed");
                    }
                }
                else{
                    orderStatus.setText("Please Login to Buy Now");
                }
            }
        });
        GridPane gridPane=new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setStyle("-fx-background-color: #000000");
        gridPane.add(addToCart,1,1);
        gridPane.add(buyNow,2,1);
        gridPane.add(orderStatus,3,1);
        gridPane.setTranslateY(headerbar+height+5);
        return gridPane;
    }

    public static void main(String[] args) {
        launch();
    }
}