package com.unstuck.supply_chain_management;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Product_Details {

    public TableView<Product> productTable;
    public Pane getAllProducts(){
        TableColumn id=new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name=new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price=new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> products= Product.getAllProduct();

        productTable=new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(Supply_Chain.width,Supply_Chain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane=new Pane();
        tablePane.setStyle("-fx-background-color: #ed872d");
        tablePane.setMinSize(Supply_Chain.width,Supply_Chain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
}
